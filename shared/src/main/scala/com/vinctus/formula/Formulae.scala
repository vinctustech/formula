package com.vinctus.formula

import scala.collection.mutable
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

class Formulae(decls: String):
  private val env: Map[String, Decl] =
    val ds: Seq[Decl] = FormulaParser.parseFormulae(decls)
    val s = new mutable.HashSet[String]

    for d <- ds do
      if s contains d.name then problem(d.pos, s"duplicate name '${d.name}'")
      s += d.name

    (ds map (d => (d.name, d)) toMap) ++ Builtin

  @JSExport
  def formula(name: String): Any =
    env get name match
      case Some(Formula(_, expr)) => eval(expr, env, env, false)
      case _                      => sys.error(s"formula '$name' not found")

  @JSExport
  def func(name: String, args: Any*): Any =
    env get name match
      case Some(Def(_, params, expr)) =>
        if args.length < params.length then sys.error(s"too few arguments, '$name' takes ${params.length} parameters")
        if args.length > params.length then sys.error(s"too many arguments, '$name' takes ${params.length} parameters")

        val locals = params zip args map { case (p, a) => (p, Val(p, a)) }

        eval(expr, env ++ locals, env, false)
      case _ => sys.error(s"function '$name' not found")

  def set(name: String, value: () => Any): Unit =
    require(value != null, "can't assign null to a variable")

    env get name match
      case Some(v: Var) => v.value = value
      case _            => sys.error(s"variable '$name' not found")

  @JSExport
  def get(name: String): Any = lookup(name, null, env, env, false)

  @JSExport
  def expression(expr: String): Any = eval(FormulaParser.parseExpr(expr), env, env, false)
