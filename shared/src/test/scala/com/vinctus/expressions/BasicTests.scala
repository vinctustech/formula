package com.vinctus.expressions

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class BasicTests extends AnyFreeSpec with Matchers:

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
