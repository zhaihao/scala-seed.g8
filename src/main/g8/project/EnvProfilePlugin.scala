/*
 * Copyright (c) 2020.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

import com.lightbend.sbt.javaagent.JavaAgentPackaging.NoPlugin
import sbt.Keys._
import sbt._

import scala.Console.{RESET, YELLOW}

/**
 * EnvProfilePlugin
 *
 * like maven profile
 *
 * @author zhaihao
 * @version 1.0
 * @since 2020/11/23 10:46 上午
 */
object EnvProfilePlugin extends AutoPlugin {

  override def trigger = NoTrigger

  override def requires = NoPlugin

  object autoImport {

    object EnvProfile extends Enumeration {
      val prod, stage, test, dev = Value
    }
    val envProfile = settingKey[EnvProfile.Value]("the current build environment")
  }

  import autoImport._

  override def projectSettings: Seq[Setting[_]] = Seq(
    envProfile := {
      sys.props
        .get("env")
        .orElse(sys.env.get("BUILD_ENV"))
        .flatMap(e => Some(EnvProfile.withName(e)))
        .getOrElse(EnvProfile.dev)
    },
    // give a feed back
    onLoadMessage := {
      // append on the old message as well
      val defaultMessage = onLoadMessage.value

      s"""|$defaultMessage
          |Running in environment profile: [ $YELLOW${envProfile.value}$RESET ]""".stripMargin
    },
    unmanagedResourceDirectories in Compile += (resourceDirectory in Compile).value.getParentFile / "common",
    resourceDirectory in Compile            := (resourceDirectory in Compile).value / envProfile.value.toString
  )
}
