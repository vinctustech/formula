package com.vinctus.formula

import math.*
import scala.language.postfixOps

val Builtin =
  List(
    Function("abs", { case Seq(x: Double) => x.abs }),
    Function("acos", { case Seq(x: Double) => acos(x) }),
    Function("asin", { case Seq(x: Double) => asin(x) }),
    Function("atan", { case Seq(x: Double) => atan(x) }),
    Function("atan2", { case Seq(x: Double, y: Double) => atan2(x, y) }),
    Function("ceil", { case Seq(x: Double) => x.ceil }),
    Function("cos", { case Seq(x: Double) => cos(x) }),
    Function("exp", { case Seq(x: Double) => exp(x) }),
    Function("floor", { case Seq(x: Double) => x.floor }),
    Function("log", { case Seq(x: Double) => log10(x) }),
    Function("ln", { case Seq(x: Double) => log(x) }),
    Function("pow", { case Seq(x: Double, y: Double) => pow(x, y) }),
    Function("round", { case Seq(x: Double) => x.round.toDouble }),
    Function("sign", { case Seq(x: Double) => x.sign }),
    Function("sin", { case Seq(x: Double) => sin(x) }),
    Function("sqrt", { case Seq(x: Double) => sqrt(x) }),
    Function("tan", { case Seq(x: Double) => tan(x) }),
    Val("pi", Pi),
    Val("e", E),
  ) map (f => (f.name, f)) toMap
