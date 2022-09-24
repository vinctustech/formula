package com.vinctus.formula

def parseExpr(input: String): String = pprt(FormulaParser.parseExpr(input))

def pprt(x: Any): String = pprint.PPrinter.BlackWhite.tokenize(x).mkString
