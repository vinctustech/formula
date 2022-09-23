package com.vinctus.expressions

import scala.util.parsing.combinator.{ImplicitConversions, PackratParsers}
import scala.util.parsing.combinator.lexical.StdLexical
import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import scala.util.parsing.input.CharSequenceReader

object ExpressionParser extends StandardTokenParsers with PackratParsers with ImplicitConversions:

  import AST.*
  import Expr.*

  override val lexical = new ExpressionLexer

  def parse(input: String): Expr =
    phrase(expression)(new lexical.Scanner(new PackratReader(new CharSequenceReader(input)))) match {
      case Success(ast, _) => ast
      case e: NoSuccess    => sys.error(s"parse error: $e")
    }

  // https://github.com/scala/scala-parser-combinators/blob/v2.1.1/shared/src/main/scala/scala/util/parsing/combinator/JavaTokenParsers.scala

  lexical.reserved ++= ("""
      |if
      |then
      |else
      |""".trim.stripMargin split "\\s+")
  lexical.delimiters ++= ("+ - * / ( ) , == != =" split ' ')

  type P[+T] = PackratParser[T]

  lazy val expression: P[Expr] = additive

  lazy val additive: P[Expr] = positioned(
    additive ~ "+" ~ multiplicative ^^ Binary.apply |
      multiplicative
  )

  lazy val multiplicative: P[Expr] = positioned(
    multiplicative ~ "*" ~ primary ^^ Binary.apply |
      primary
  )

  lazy val primary: P[Expr] = positioned(
    ident ^^ Variable.apply
      | numericLit ^^ NumericLit.apply
      | stringLit ^^ StringLit.apply
      | "(" ~> expression <~ ")"
  )
