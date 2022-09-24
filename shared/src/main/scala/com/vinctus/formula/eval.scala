package com.vinctus.formula

import AST.Expr.*

def eval(e: AST.Expr, env: collection.Map[String, Decl], ctx: collection.Map[String, Decl], pure: Boolean): Any =
  e match
    case NumericLit(n) => n.toDouble
    case Name(name) =>
      env.getOrElse(name, sys.error(s"unknown variable or constant '$name'")) match
        case Val(_, value) => value
        case v @ Var(_, expr, value) =>
          if pure then sys.error(s"referentially opaque: variable '$name' referenced")
          if value == null then
            if expr == null then sys.error(s"variable '$name' has not been set")
            v.value = eval(expr, env, ctx, false)
            v.value
          else value
        case c @ Const(_, expr, value) =>
          if value == null then
            c.value = eval(expr, env, ctx, true)
            c.value
          else value
    case Apply(name, args) =>
      env.getOrElse(name, sys.error(s"unknown function '$name'")) match
        case _: Var => sys.error(s"variable '$name' doesn't take an argument list")
        case Def(name, params, func) =>
          if args.length < params.length then sys.error(s"too few arguments, '$name' takes ${params.length} parameters")
          if args.length > params.length then
            sys.error(s"too many arguments, '$name' takes ${params.length} parameters")

          val locals = params zip args map { case (p, a) => (p, Val(p, eval(a, env, ctx, pure))) }

          eval(func, ctx ++ locals, ctx, pure)
        case Function(_, func) => func(args map (a => eval(a, env, ctx, pure)))
    case Binary(left, op, right) =>
      (eval(left, env, ctx, pure), op, eval(right, env, ctx, pure)) match
        case (a: String, "+", b: String) => a ++ b
        case (a: Double, "+", b: Double) => a + b
        case (a: Double, "-", b: Double) => a - b
        case (a: Double, "*", b: Double) => a * b
        case (a: Double, "/", b: Double) => a / b
    case Unary("-", expr) => -eval(expr, env, ctx, pure).asInstanceOf[Double]
