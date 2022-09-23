package com.vinctus.expressions

def parse(input: String): String =
  pprint.PPrinter.BlackWhite.tokenize(ExpressionParser.parse(input)).mkString
