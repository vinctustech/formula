package com.vinctus.formula

class Formulae(decls: String):
  private val env = (ExpressionParser.parseFormulae(decls) map (d => (d.name, d)) toMap) ++ Builtin

  def formula(name: String): String =
    env get name match
      case Some(Formula(_, expr)) => render(eval(expr, env, env, false))
      case _                      => sys.error(s"formula '$name' not found")
