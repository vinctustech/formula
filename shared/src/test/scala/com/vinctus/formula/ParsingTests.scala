package com.vinctus.formula

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class ParsingTests extends AnyFreeSpec with Matchers:

  "add" in {
    parseExpr("3 + a") shouldBe
      """
        |Binary(left = NumericLit(n = "3"), op = "+", right = Name(name = "a"))
        """.trim.stripMargin
  }

  "mul" in {
    parseExpr("3 * a") shouldBe
      """
        |Binary(left = NumericLit(n = "3"), op = "*", right = Name(name = "a"))
        """.trim.stripMargin
  }

  "add mul" in {
    parseExpr("3 + a * 4") shouldBe
      """
        |Binary(
        |  left = NumericLit(n = "3"),
        |  op = "+",
        |  right = Binary(left = Name(name = "a"), op = "*", right = NumericLit(n = "4"))
        |)
        |""".stripMargin.trim
  }

  "add mul paren" in {
    parseExpr("(a + b) * 4") shouldBe
      """
        |Binary(
        |  left = Binary(left = Name(name = "a"), op = "+", right = Name(name = "b")),
        |  op = "*",
        |  right = NumericLit(n = "4")
        |)
        |""".stripMargin.trim
  }

  "sub" in {
    parseExpr("3 - a") shouldBe
      """
        |Binary(left = NumericLit(n = "3"), op = "-", right = Name(name = "a"))
        """.trim.stripMargin
  }

  "div" in {
    parseExpr("3 / a") shouldBe
      """
        |Binary(left = NumericLit(n = "3"), op = "/", right = Name(name = "a"))
        """.trim.stripMargin
  }

  "sub div" in {
    parseExpr("3 - a / 4") shouldBe
      """
        |Binary(
        |  left = NumericLit(n = "3"),
        |  op = "-",
        |  right = Binary(left = Name(name = "a"), op = "/", right = NumericLit(n = "4"))
        |)
        |""".stripMargin.trim
  }

  "sub div paren" in {
    parseExpr("(a - b) / 4") shouldBe
      """
        |Binary(
        |  left = Binary(left = Name(name = "a"), op = "-", right = Name(name = "b")),
        |  op = "/",
        |  right = NumericLit(n = "4")
        |)
        |""".stripMargin.trim
  }

  "def" in {
    parseExpr("f()") shouldBe
      """
        |Apply(name = "f", args = List())
        """.trim.stripMargin
  }

  "def arg" in {
    parseExpr("f(3)") shouldBe
      """
        |Apply(name = "f", args = List(NumericLit(n = "3")))
        """.trim.stripMargin
  }

  "def args" in {
    parseExpr("f(3, a)") shouldBe
      """
        |Apply(name = "f", args = List(NumericLit(n = "3"), Name(name = "a")))
        """.trim.stripMargin
  }
