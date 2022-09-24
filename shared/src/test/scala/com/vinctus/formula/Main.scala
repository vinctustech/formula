package com.vinctus.formula

@main def run(): Unit =
  val env: Map[String, Decl] =
    Builtin
  val input = "round(pi)"
  val ast = ExpressionParser.parseExpr(input)

  println(ast)
  println(render(eval(ast, env, env, false)))
