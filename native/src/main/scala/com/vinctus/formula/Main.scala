package com.vinctus.formula

import scopt.OParser

import java.io.File
import scala.util.{Try, Using}

type Action = "formula"
case class Config(input: File, vars: Seq[(String, String)], actions: Seq[(Action, String)])
val stringRegex = "'([^']*)'|\"([^\"]*)\"".r

@main def run(args: String*): Unit =
  val builder = OParser.builder[Config]
  val parser = {
    import builder._
    OParser.sequence(
      programName("formula"),
      head("formula", "0.0.1"),
      arg[File]("<file>")
        .action((x, c) => c.copy(input = x))
        .text("file containing declarations"),
      opt[String]('f', "formula")
        .unbounded()
        .valueName("<name>")
        .action((x, c) => c.copy(actions = c.actions :+ ("formula", x)))
        .text("out is a required file property"),
      opt[Seq[(String, String)]]('s', "set")
        .valueName("k1=v1,k2=v2...")
        .action((x, c) => c.copy(vars = x))
        .text("out is a required file property"),
      help("help").text("prints this usage text"),
    )
  }

  OParser.parse(parser, args, Config(null, Nil, Nil)) match {
    case Some(Config(file, vars, actions)) =>
      val f = new Formulae(Using(io.Source.fromFile(file))(_.mkString).get)

      vars foreach {
        case (name, stringRegex(s, null))   => f.set(name, s)
        case (name, stringRegex(null, s))   => f.set(name, s)
        case (name, b @ ("true" | "false")) => f.set(name, b == "true")
        case (name, n)                      => f.set(name, n.toDouble)
      }

      actions foreach { case ("formula", name) => println(s"formula $name = ${f.formula(name)}") }
    case _ =>
  }
