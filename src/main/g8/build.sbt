import sbt.Keys.scalacOptions
// global
scalaVersion in Global := "$scala_version$"
organization in Global := "$organization_name$"

scalacOptions in Global ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")
resolvers in Global += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
externalResolvers in Global := Resolver.combineDefaultResolvers(resolvers.value.toVector,
                                                                mavenCentral = true)

libraryDependencies in Global ++= Seq(orison, scalatest)
libraryDependencies in Global ++= log
excludeDependencies in Global ++= excludes
dependencyOverrides in Global ++= overrides

cancelable in Global := true
//

lazy val root = (project in file("."))
  .settings(
    moduleName          := "$name$",
    name                := "$name$",
    logBuffered in Test := false,
    libraryDependencies ++= Seq().flatten,
    libraryDependencies ++= Seq()
  )

