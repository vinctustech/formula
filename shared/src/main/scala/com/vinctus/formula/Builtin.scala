package com.vinctus.formula

import math.*

val Builtin =
  List(
    Function("round", { case Seq(x: Double) => x.round.toDouble }),
    Function("abs", { case Seq(x: Double) => x.abs }),
    Function("ceil", { case Seq(x: Double) => x.ceil }),
    Function("floor", { case Seq(x: Double) => x.floor }),
    Function("sign", { case Seq(x: Double) => x.sign }),
    Function("sin", { case Seq(x: Double) => sin(x) }),
    Function("cos", { case Seq(x: Double) => cos(x) }),
    Function("tan", { case Seq(x: Double) => tan(x) }),
    Function("asin", { case Seq(x: Double) => asin(x) }),
    Function("acos", { case Seq(x: Double) => acos(x) }),
    Function("atan", { case Seq(x: Double) => atan(x) }),
    Function("atan2", { case Seq(x: Double, y: Double) => atan2(x, y) }),
    Function("exp", { case Seq(x: Double) => exp(x) }),
    Function("ln", { case Seq(x: Double) => log(x) }),
    Function("log", { case Seq(x: Double) => log10(x) }),
    Val("pi", Pi),
    Val("e", E),
  ) map (f => (f.name, f)) toMap
