package com.vinctus.formula

def lookup(
    name: String,
    e: AST.Expr,
    env: collection.Map[String, Decl],
    ctx: collection.Map[String, Decl],
    pure: Boolean,
): Any =
  env.getOrElse(name, problem(e.pos, s"unknown variable or constant '$name'")) match
    case Val(_, value) => value
    case v @ Var(_, expr, value) =>
      if pure then problem(e.pos, s"referentially opaque: variable '$name' referenced")
      if value == null then
        if expr == null then problem(e.pos, s"variable '$name' has not been set")
        v.value = eval(expr, env, ctx, false)
        v.value
      else value
    case c @ Const(_, expr, value) =>
      if value == null then
        c.value = eval(expr, env, ctx, true)
        c.value
      else value
    case Formula(_, expr) => eval(expr, env, env, pure)
