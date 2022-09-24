package com.vinctus.formula

import scala.util.parsing.input.Positional

object AST:
  enum Expr extends Positional:
    case Binary(left: Expr, op: String, right: Expr) extends Expr
    case Unary(op: String, expr: Expr) extends Expr
    case Name(name: String) extends Expr
    case NumericLit(n: String) extends Expr
    case StringLit(s: String) extends Expr
    case Apply(name: String, args: Seq[Expr]) extends Expr
