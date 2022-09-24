package com.vinctus.formula

import AST.Expr.*

def eval(e: AST.Expr, env: collection.Map[String, Decl], pure: Boolean): Any =
  e match
    case NumericLit(n) => n.toDouble
    case Binary(left, op, right) =>
      val l = eval(left, env, pure)
      val r = eval(right, env, pure)

      (l, op, r) match
        case (a: String, "+", b: String) => a ++ b
        case (a: Double, "+", b: Double) => a + b
        case (a: Double, "-", b: Double) => a - b
        case (a: Double, "*", b: Double) => a * b
        case (a: Double, "/", b: Double) => a / b
