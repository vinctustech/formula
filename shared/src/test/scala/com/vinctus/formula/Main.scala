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
      |formula u = x + f(5) < 10 ? 'yes' : 'no'
      |""".stripMargin,
    )

  println(f.expression("cos((pi ^ .5) ^ 2) == -1"))
  println(f.get("default"))
