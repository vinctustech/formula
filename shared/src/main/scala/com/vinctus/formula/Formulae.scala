package com.vinctus.formula

import scala.collection.immutable.VectorMap

class Formulae(decls: String):
  val env: Map[String, Decl] = (ExpressionParser.parseFormulae(decls) map (d => (d.name, d)) to VectorMap) ++ Builtin

  def formula(name: String): String =
    env get name match
      case Some(Formula(_, expr)) => render(eval(expr, env, env, false))
      case _                      => sys.error(s"formula '$name' not found")

  def set(name: String, value: Any): Unit =
    env get name match
      case Some(v: Var) => v.value = value
      case _            => sys.error(s"variable '$name' not found")
