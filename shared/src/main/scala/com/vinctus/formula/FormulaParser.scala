package com.vinctus.formula

import scala.util.parsing.combinator.{ImplicitConversions, PackratParsers}
import scala.util.parsing.combinator.lexical.StdLexical
import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import scala.util.parsing.input.CharSequenceReader

object FormulaParser extends StandardTokenParsers with PackratParsers with ImplicitConversions:

  import AST.*
  import Expr.*

  override val lexical = new FormulaLexer

  def parseExpr(input: String): Expr =
    phrase(expression)(new lexical.Scanner(new PackratReader(new CharSequenceReader(input)))) match {
      case Success(ast, _) => ast
      case e: NoSuccess    => sys.error(s"parse error: $e")
    }

  def parseFormulae(input: String): Seq[Decl] =
    phrase(formulae)(new lexical.Scanner(new PackratReader(new CharSequenceReader(input)))) match {
      case Success(decls, _) => decls
      case e: NoSuccess      => sys.error(s"parse error: $e")
    }

  lexical.reserved ++= ("""
      |const
      |def
      |formula
      |var
      |mod
      |""".trim.stripMargin split "\\s+")
  lexical.delimiters ++= ("+ - * / ( ) , < <= > >= ? : == != =" split ' ')

  type P[+T] = PackratParser[T]

  lazy val formulae: P[Seq[Decl]] = rep1(declaration)

  lazy val declaration: P[Decl] =
    "formula" ~> ident ~ ("=" ~> expression) ^^ Formula.apply
      | "const" ~> ident ~ "=" ~ expression ^^ { case n ~ _ ~ e => Const(n, e, null) }
      | "def" ~> ident ~ ("(" ~> rep1sep(ident, ",") <~ ")") ~ ("=" ~> expression) ^^ Def.apply
      | "var" ~> ident ~ "=" ~ expression ^^ { case n ~ _ ~ e => Var(n, e, null) }

  lazy val expression: P[Expr] = additive

  lazy val additive: P[Expr] = positioned(
    additive ~ ("+" | "-") ~ multiplicative ^^ Binary.apply
      | multiplicative,
  )

  lazy val multiplicative: P[Expr] = positioned(
    multiplicative ~ ("*" | "/" | "mod") ~ prefix ^^ Binary.apply
      | prefix,
  )

  lazy val prefix: P[Expr] = positioned(
    "-" ~ applicative ^^ Unary.apply
      | applicative,
  )

  lazy val applicative: P[Expr] = positioned(
    ident ~ ("(" ~> repsep(expression, ",") <~ ")") ^^ Apply.apply
      | primary,
  )

  lazy val primary: P[Expr] = positioned(
    ident ^^ Name.apply
      | numericLit ^^ NumericLit.apply
      | stringLit ^^ StringLit.apply
      | "(" ~> expression <~ ")",
  )
