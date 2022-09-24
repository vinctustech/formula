package com.vinctus.formula

import AST.Expr.*

def eval(e: AST.Expr, env: collection.Map[String, Decl], pure: Boolean): Any =
  e match
    case NumericLit(n) => n.toDouble
    case Name(name) =>
      env.getOrElse(name, sys.error(s"unknown variable or constant '$name'")) match
        case Val(_, value) => value
    case Apply(name, args) =>
      env.getOrElse(name, sys.error(s"unknown function '$name'")) match
        case _: Var                  => sys.error(s"variable '$name' doesn't take an argument list")
        case Def(name, params, func) =>
        case Function(_, func)       => func(args map (a => eval(a, env, pure)))
    case Binary(left, op, right) =>
      val l = eval(left, env, pure)
      val r = eval(right, env, pure)

      (l, op, r) match
        case (a: String, "+", b: String) => a ++ b
        case (a: Double, "+", b: Double) => a + b
        case (a: Double, "-", b: Double) => a - b
        case (a: Double, "*", b: Double) => a * b
        case (a: Double, "/", b: Double) => a / b
