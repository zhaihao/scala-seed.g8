/*
 * Copyright (c) 2019.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit http://ooon.me or mail to zhaihao@ooon.me
 */

import sbt._

/**
  * Dependencies
  *
  * @author zhaihao
  * @version 1.0 2019-02-18 13:29
  */
object Dependencies extends AutoPlugin {
  override def requires = empty
  override def trigger  = allRequirements

  object autoImport {
    lazy val orison    = "me.ooon"           %% "orison"        % "0.0.24"
    lazy val scalatest = "org.scalatest"     %% "scalatest"     % "3.2.0-M4" % Test
    lazy val os_lib    = "com.lihaoyi"       %% "os-lib"        % "0.7.0"
    lazy val sqlite    = "org.xerial"        % "sqlite-jdbc"    % "3.31.1"
    lazy val requests  = "com.lihaoyi"       %% "requests"      % "0.6.2"
    lazy val play_json = "com.typesafe.play" %% "play-json"     % "2.8.1"
    lazy val scraper   = "net.ruippeixotog"  %% "scala-scraper" % "2.2.0"
    lazy val scalaz    = "org.scalaz"        %% "scalaz-core"   % "7.2.27"
    lazy val squants   = "org.typelevel"     %% "squants"       % "1.6.0"

    lazy val log = Seq(
      "com.typesafe.scala-logging" %% "scala-logging"  % "3.9.2",
      "ch.qos.logback"             % "logback-classic" % "1.2.3"
    )

    lazy val chill = Seq(
      "com.twitter" %% "chill"           % "0.9.3",
      "com.twitter" %% "chill-bijection" % "0.9.3"
    )

    // https://github.com/ghik/silencer
    lazy val silencer = Seq(
      compilerPlugin("com.github.ghik" %% "silencer-plugin" % "0.6"),
      "com.github.ghik" %% "silencer-lib" % "0.6"
    )

    lazy val slick = Seq(
      "com.typesafe.slick" %% "slick"          % "3.3.0",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",
      "com.typesafe.slick" %% "slick-codegen"  % "3.3.0",
      "com.typesafe.slick" %% "slick-testkit"  % "3.3.0" % Test
    )

    lazy val breeze = Seq(
      "org.scalanlp" %% "breeze"         % "1.0-RC2",
      "org.scalanlp" %% "breeze-natives" % "1.0-RC2",
      "org.scalanlp" %% "breeze-viz"     % "1.0-RC2"
    )

    val excludes = Seq(
      ExclusionRule("org.slf4j", "slf4j-log4j12"),
      ExclusionRule("log4j", "log4j")
    )

    val overrides = Seq()
  }

}
