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
        |formula u = x + f(5.3)
        |""".stripMargin,
    )

  "env" in {
    pprt(f.env) shouldBe
      """
        |VectorMap(
        |  "default" -> Const(name = "default", expr = NumericLit(n = "3"), value = null),
        |  "x" -> Var(name = "x", expr = Name(name = "default"), value = null),
        |  "f" -> Def(
        |    name = "f",
        |    params = List("a"),
        |    func = Binary(left = Name(name = "a"), op = "+", right = NumericLit(n = "4"))
        |  ),
        |  "u" -> Formula(
        |    name = "u",
        |    expr = Binary(
        |      left = Name(name = "x"),
        |      op = "+",
        |      right = Apply(name = "f", args = List(NumericLit(n = "5")))
        |    )
        |  ),
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

  "formula" in {
    render(f.formula("u")) shouldBe "12"
  }

  "set" in {
    f.set("x", 3.5)
    f.formula("u") shouldBe 12.5
  }
