package com.vinctus.formula

def evalExpr(input: String): String =
  render(eval(FormulaParser.parseExpr(input), Builtin, Builtin, false))
