package com.vinctus.expressions

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class ParsingTests extends AnyFreeSpec with Matchers:

  "add" in {
    parse("3 + a") shouldBe
      """
        |Binary(left = NumericLit(n = "3"), op = "+", right = Variable(name = "a"))
        """.trim.stripMargin
  }

  "mul" in {
    parse("3 * a") shouldBe
      """
        |Binary(left = NumericLit(n = "3"), op = "*", right = Variable(name = "a"))
        """.trim.stripMargin
  }

  "add mul" in {
    parse("3 + a * 4") shouldBe
      """
        |Binary(
        |  left = NumericLit(n = "3"),
        |  op = "+",
        |  right = Binary(left = Variable(name = "a"), op = "*", right = NumericLit(n = "4"))
        |)
        |""".stripMargin.trim
  }

  "add mul paren" in {
    parse("(a + b) * 4") shouldBe
      """
        |Binary(
        |  left = Binary(left = Variable(name = "a"), op = "+", right = Variable(name = "b")),
        |  op = "*",
        |  right = NumericLit(n = "4")
        |)
        |""".stripMargin.trim
  }

  "sub" in {
    parse("3 - a") shouldBe
      """
        |Binary(left = NumericLit(n = "3"), op = "-", right = Variable(name = "a"))
        """.trim.stripMargin
  }

  "div" in {
    parse("3 / a") shouldBe
      """
        |Binary(left = NumericLit(n = "3"), op = "/", right = Variable(name = "a"))
        """.trim.stripMargin
  }

  "sub div" in {
    parse("3 - a / 4") shouldBe
      """
        |Binary(
        |  left = NumericLit(n = "3"),
        |  op = "-",
        |  right = Binary(left = Variable(name = "a"), op = "/", right = NumericLit(n = "4"))
        |)
        |""".stripMargin.trim
  }

  "sub div paren" in {
    parse("(a - b) / 4") shouldBe
      """
        |Binary(
        |  left = Binary(left = Variable(name = "a"), op = "-", right = Variable(name = "b")),
        |  op = "/",
        |  right = NumericLit(n = "4")
        |)
        |""".stripMargin.trim
  }

  "func" in {
    parse("f()") shouldBe
      """
        |Apply(name = "f", args = List())
        """.trim.stripMargin
  }

  "func arg" in {
    parse("f(3)") shouldBe
      """
        |Apply(name = "f", args = List(NumericLit(n = "3")))
        """.trim.stripMargin
  }

  "func args" in {
    parse("f(3, a)") shouldBe
      """
        |Apply(name = "f", args = List(NumericLit(n = "3"), Variable(name = "a")))
        """.trim.stripMargin
  }
