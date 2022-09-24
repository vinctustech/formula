package com.vinctus.formula

import AST.Expr.*

def render: PartialFunction[Any, String] = {
  case d: Double if d.isWhole => f"$d%.0f"
  case x                      => x.toString
}
