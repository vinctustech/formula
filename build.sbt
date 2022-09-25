ThisBuild / licenses += "ISC" -> url("https://opensource.org/licenses/ISC")
ThisBuild / versionScheme := Some("semver-spec")

publish / skip := true

lazy val formula = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("."))
  .settings(
    name := "formula",
    version := "0.0.12",
    scalaVersion := "3.2.0",
    scalacOptions ++=
      Seq(
        "-deprecation",
        "-feature",
        "-unchecked",
        "-language:postfixOps",
        "-language:implicitConversions",
        "-language:existentials",
        "-language:dynamics",
      ),
    organization := "io.github.vinctustech",
    githubOwner := "vinctustech",
    githubRepository := name.value,
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.13" % "test",
    libraryDependencies += "org.scala-lang.modules" %%% "scala-parser-combinators" % "2.1.1",
//    libraryDependencies ++= Seq(
//      "io.github.edadma" %%% "cross-platform" % "0.1.1"
//    ),
    libraryDependencies ++= Seq(
      // "com.github.scopt" %%% "scopt" % "4.1.0",
      "com.lihaoyi" %%% "pprint" % "0.7.3" % "test",
    ),
    publishMavenStyle := true,
    Test / publishArtifact := false,
    licenses += "ISC" -> url("https://opensource.org/licenses/ISC"),
  )
  .jvmSettings(
    libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.1.0" % "provided",
  )
  .nativeSettings(
    nativeLinkStubs := true,
    libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.1.0" % "provided",
    libraryDependencies ++= Seq(
      "com.github.scopt" %%% "scopt" % "4.1.0",
    ),
  )
  .jsSettings(
//    mainClass := Some("com.vinctus.formula.run"),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
//    Test / scalaJSUseMainModuleInitializer := true,
//    Test / scalaJSUseTestModuleInitializer := false,
//    Test / scalaJSUseMainModuleInitializer := false,
//    Test / scalaJSUseTestModuleInitializer := true,
//    scalaJSUseMainModuleInitializer := true,
  )
