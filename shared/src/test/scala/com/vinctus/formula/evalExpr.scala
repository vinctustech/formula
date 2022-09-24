package com.vinctus.formula

def evalExpr(input: String): String =
  render(eval(ExpressionParser.parseExpr(input), Map(), false))
