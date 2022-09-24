package com.vinctus.formula

abstract class Decl:
  val name: String

class Holder(var v: Any)

case class Const(name: String, expr: AST.Expr, var value: Any) extends Decl
case class Var(name: String, value: Holder) extends Decl
case class Def(name: String, params: Seq[String], func: AST.Expr) extends Decl
case class Formula(name: String, value: AST.Expr) extends Decl
case class Function(name: String, func: PartialFunction[Seq[Any], Any]) extends Decl
case class Val(name: String, value: Any) extends Decl
