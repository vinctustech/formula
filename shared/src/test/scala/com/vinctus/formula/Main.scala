package com.vinctus.formula

import pprint.pprintln

@main def run(): Unit =
  val f =
    new Formulae(
      """
      |const default = 3.5
      |
      |var x = default
      |
      |def f(a) = a + 4
      |
      |def u = x + f(5)
      |def v = 1 + u%
      |""".stripMargin,
    )

  println(f.formula("u"))
  println(f.formula("v"))
