package com.vinctus.formula

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class FormulaeTests extends AnyFreeSpec with Matchers:
  val f =
    new Formulae(
      """
        |const default = 3.1
        |
        |var x = default
        |
        |def f(a) = a + 4.2
        |
        |def u = x + f(5.3)
        |""".stripMargin,
    )

  "formula" in {
    f.formula("u") shouldBe 12.6
  }

  "set" in {
    f.set("x", () => 3.5)
    f.formula("u") shouldBe 13.0
  }

  "evaluate" in {
    f.evaluate("cos(f(3))") shouldBe 0.6083513145322546
  }
