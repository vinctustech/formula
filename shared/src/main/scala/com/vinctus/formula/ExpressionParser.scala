package com.vinctus.formula

import scala.util.parsing.combinator.{ImplicitConversions, PackratParsers}
import scala.util.parsing.combinator.lexical.StdLexical
import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import scala.util.parsing.input.CharSequenceReader

object ExpressionParser extends StandardTokenParsers with PackratParsers with ImplicitConversions:

  import AST.*
  import Expr.*

  override val lexical = new ExpressionLexer

  def parseExpr(input: String): Expr =
    phrase(expression)(new lexical.Scanner(new PackratReader(new CharSequenceReader(input)))) match {
      case Success(ast, _) => ast
      case e: NoSuccess    => sys.error(s"parse error: $e")
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

  lazy val expression: P[Expr] = additive

  lazy val additive: P[Expr] = positioned(
    additive ~ ("+" | "-") ~ multiplicative ^^ Binary.apply
      | multiplicative,
  )

  lazy val multiplicative: P[Expr] = positioned(
    multiplicative ~ ("*" | "/" | "mod") ~ applicative ^^ Binary.apply
      | applicative,
  )

  lazy val applicative: P[Expr] = positioned(
    ident ~ "(" ~ repsep(expression, ",") ~ ")" ^^ { case n ~ _ ~ args ~ _ => Apply(n, args) }
      | primary,
  )

  lazy val primary: P[Expr] = positioned(
    ident ^^ Name.apply
      | numericLit ^^ NumericLit.apply
      | stringLit ^^ StringLit.apply
      | "(" ~> expression <~ ")",
  )
