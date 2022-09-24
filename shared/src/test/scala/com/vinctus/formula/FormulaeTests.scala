package com.vinctus.formula

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class FormulaeTests extends AnyFreeSpec with Matchers:
  val f =
    new Formulae(
      """
        |def f(a) = a + 1
        |
        |formula u = f(3)
        |""".stripMargin,
    )

  "env" in {
    pprt(f.env) shouldBe
      """
        |VectorMap(
        |  "f" -> Def(
        |    name = "f",
        |    params = List("a"),
        |    func = Binary(left = Name(name = "a"), op = "+", right = NumericLit(n = "1"))
        |  ),
        |  "u" -> Formula(name = "u", expr = Apply(name = "f", args = List(NumericLit(n = "3")))),
        |  "e" -> Val(name = "e", value = 2.718281828459045),
        |  "pi" -> Val(name = "pi", value = 3.141592653589793),
        |  "asin" -> Function(name = "asin", func = <function1>),
        |  "cos" -> Function(name = "cos", func = <function1>),
        |  "ceil" -> Function(name = "ceil", func = <function1>),
        |  "atan2" -> Function(name = "atan2", func = <function1>),
        |  "floor" -> Function(name = "floor", func = <function1>),
        |  "round" -> Function(name = "round", func = <function1>),
        |  "sin" -> Function(name = "sin", func = <function1>),
        |  "exp" -> Function(name = "exp", func = <function1>),
        |  "atan" -> Function(name = "atan", func = <function1>),
        |  "sign" -> Function(name = "sign", func = <function1>),
        |  "ln" -> Function(name = "ln", func = <function1>),
        |  "tan" -> Function(name = "tan", func = <function1>),
        |  "abs" -> Function(name = "abs", func = <function1>),
        |  "log" -> Function(name = "log", func = <function1>),
        |  "acos" -> Function(name = "acos", func = <function1>)
        |)
        |""".stripMargin.trim
  }
