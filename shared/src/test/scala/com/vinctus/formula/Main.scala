package com.vinctus.formula

import pprint.pprintln

@main def run(): Unit =
  val f =
    new Formulae(
      """
      |def f(a) = a + 1
      |
      |formula u = f(3)
      |""".stripMargin,
    )

  println(f.formula("u"))
