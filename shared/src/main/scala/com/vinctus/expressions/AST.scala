package com.vinctus.expressions

import scala.util.parsing.input.Positional

object AST:
  enum Expr extends Positional:
    case Binary(left: Expr, op: String, right: Expr) extends Expr
    case Unary(expr: Expr, op: String) extends Expr
    case Variable(name: String) extends Expr
    case NumericLit(n: String) extends Expr
    case StringLit(s: String) extends Expr
    case Apply(name: String, args: Seq[Expr]) extends Expr
