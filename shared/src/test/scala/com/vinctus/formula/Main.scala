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
      |formula u = x + f(5)
      |""".stripMargin,
    )

  println(f.get("default").getClass)
