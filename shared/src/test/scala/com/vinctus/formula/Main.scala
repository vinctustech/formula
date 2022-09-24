package com.vinctus.formula

@main def run(): Unit =
  val env: Map[String, Decl] =
    Map()
  val input = "(3 + 4) / 2"
  val ast = ExpressionParser.parseExpr(input)

  println(ast)
  println(render(eval(ast, env, false)))
