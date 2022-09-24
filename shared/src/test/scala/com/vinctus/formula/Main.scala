package com.vinctus.formula

import pprint.pprintln

@main def run(): Unit =
  val f =
    new Formulae(
      """
      |const default = 3
      |
      |var x = default
      |
      |def f(a) = a + 4
      |
      |formula u = x + f(5)
      |""".stripMargin,
    )

  pprintln(f.env)
  println(f.formula("u"))
