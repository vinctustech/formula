package com.vinctus.formula

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.scalajs.js

@JSExportTopLevel("Formulae")
class FormulaeJS(decls: String) extends Formulae(decls):
  @JSExport
  def set(name: String, value: js.Function0[js.Any]): Unit = super.set(name, value)
