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
    lazy val orison    = "me.ooon"           %% "orison"        % "0.0.37"
    lazy val scalatest = "org.scalatest"     %% "scalatest"     % "3.2.1" % Test

    lazy val log = Seq(
      "com.typesafe.scala-logging" %% "scala-logging"  % "3.9.2",
      "ch.qos.logback"             % "logback-classic" % "1.2.3"
    )

    val excludes = Seq(
      ExclusionRule("org.slf4j", "slf4j-log4j12"),
      ExclusionRule("log4j", "log4j")
    )

    val overrides = Seq()
  }

}
