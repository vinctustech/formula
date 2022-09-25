package com.vinctus.formula

import scala.util.parsing.input.Positional

abstract class Decl extends Positional:
  val name: String

case class Const(name: String, expr: AST.Expr, var value: Any) extends Decl
case class Var(name: String, expr: AST.Expr, var value: Any) extends Decl
case class Def(name: String, params: Seq[String], func: AST.Expr) extends Decl
case class Formula(name: String, expr: AST.Expr) extends Decl
case class Function(name: String, func: PartialFunction[Seq[Any], Any]) extends Decl
case class Val(name: String, value: Any) extends Decl
