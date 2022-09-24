package com.vinctus.expressions

abstract class Decl:
  val name: String

class Holder(var v: Any)

case class Const(name: String, value: Any) extends Decl
case class Variable(name: String, value: Holder) extends Decl
case class Function(name: String, params: Seq[String], func: AST.Expr) extends Decl
case class Formula(name: String, value: AST.Expr) extends Decl
case class Builtin(name: String, func: PartialFunction[Seq[Any], Any]) extends Decl
