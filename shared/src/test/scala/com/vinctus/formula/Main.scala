package com.vinctus.formula

import pprint.pprintln

@main def run(): Unit =
  val f =
    new Formulae(
      """
      |var x = 3
      |
      |def f(a) = a + 4
      |
      |formula u = x + f(5)
      |""".stripMargin,
    )

  pprintln(f.env)
  println(f.formula("u"))
