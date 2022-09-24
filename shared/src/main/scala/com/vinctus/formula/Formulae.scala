package com.vinctus.formula

import scala.collection.immutable.VectorMap
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Formulae")
class Formulae(decls: String):
  val env: Map[String, Decl] = (FormulaParser.parseFormulae(decls) map (d => (d.name, d)) to VectorMap) ++ Builtin

  @JSExport
  def formula(name: String): String =
    env get name match
      case Some(Formula(_, expr)) => render(eval(expr, env, env, false))
      case _                      => sys.error(s"formula '$name' not found")

  @JSExport
  def set(name: String, value: Any): Unit =
    require(value != null, "can't assign null to a variable")

    env get name match
      case Some(v: Var) => v.value = value
      case _            => sys.error(s"variable '$name' not found")
