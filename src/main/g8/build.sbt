scalaVersion     := "$scala_version$"
name             := "$name$"
organization     := "$organization_name$"

Global / excludeLintKeys := Set(idePackagePrefix)

idePackagePrefix := Some("$organization_name$.$name$")


libraryDependencies ++= Seq(NSCALA, OS_LIB, SQUANTS, ORISON, TYPESAFE_CONFIG, PLAY_JSON)
libraryDependencies ++= Seq(SCALA_TEST, LOG).flatten

excludeDependencies in Global ++= excludes
dependencyOverrides in Global ++= overrides
