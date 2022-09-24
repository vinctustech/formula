package com.vinctus.formula

def parseExpr(input: String): String =
  pprint.PPrinter.BlackWhite.tokenize(ExpressionParser.parseExpr(input)).mkString
