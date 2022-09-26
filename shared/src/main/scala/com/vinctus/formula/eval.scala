package com.vinctus.formula

import AST.Expr.*

def eval(e: AST.Expr, env: collection.Map[String, Decl], ctx: collection.Map[String, Decl], pure: Boolean): Any =
  e match
    case StringLit(s)  => s
    case NumericLit(n) => n.toDouble
    case Name(name)    => lookup(name, e, env, ctx, pure)
    case Apply(name, args) =>
      env.getOrElse(name, problem(e.pos, s"unknown function '$name'")) match
        case _: Var => problem(e.pos, s"variable '$name' doesn't take an argument list")
        case Def(name, params, func) =>
          if args.length < params.length then
            problem(e.pos, s"too few arguments, '$name' takes ${params.length} parameters")
          if args.length > params.length then
            problem(e.pos, s"too many arguments, '$name' takes ${params.length} parameters")

          val locals = params zip args map { case (p, a) => (p, Val(p, eval(a, env, ctx, pure))) }

          eval(func, ctx ++ locals, ctx, pure)
        case Function(_, func) => func(args map (a => eval(a, env, ctx, pure)))
    case Binary(left, "and", right) =>
      if eval(left, env, ctx, pure).asInstanceOf[Boolean] then eval(right, env, ctx, pure)
      else false
    case Binary(left, "or", right) =>
      if eval(left, env, ctx, pure).asInstanceOf[Boolean] then true
      else eval(right, env, ctx, pure)
    case Binary(left, op, right) =>
      (eval(left, env, ctx, pure), op, eval(right, env, ctx, pure)) match
        case (a: String, "+", b: String)   => a ++ b
        case (a: String, "<", b: String)   => a < b
        case (a: String, ">", b: String)   => a > b
        case (a: String, "<=", b: String)  => a <= b
        case (a: String, ">=", b: String)  => a >= b
        case (a: Double, "+", b: Double)   => a + b
        case (a: Double, "-", b: Double)   => a - b
        case (a: Double, "*", b: Double)   => a * b
        case (a: Double, "mod", b: Double) => a % b
        case (a: Double, "^", b: Double)   => math.pow(a, b)
        case (a: Double, "/", b: Double)   => a / b
        case (a: Double, "<", b: Double)   => a < b
        case (a: Double, ">", b: Double)   => a > b
        case (a: Double, "<=", b: Double)  => a <= b
        case (a: Double, ">=", b: Double)  => a >= b
        case (a, "==", b)                  => a == b
        case (a, "!=", b)                  => a != b
    case Unary("-", expr)   => -eval(expr, env, ctx, pure).asInstanceOf[Double]
    case Unary("not", expr) => !eval(expr, env, ctx, pure).asInstanceOf[Boolean]
    case Unary("%", expr)   => eval(expr, env, ctx, pure).asInstanceOf[Double] / 100
    case Ternary(cond, yes, no) =>
      if eval(cond, env, ctx, pure).asInstanceOf[Boolean] then eval(yes, env, ctx, pure)
      else eval(no, env, ctx, pure)
