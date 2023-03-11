Global / lintUnusedKeysOnLoad := false

scalaVersion     := "$scala_version$"
name             := "$name$"
organization     := "$organization_name$"
target		     := studioTarget.value
idePackagePrefix := Some("$organization_name$")


libraryDependencies ++= Seq(NSCALA, OS_LIB, SQUANTS, ORISON, TYPESAFE_CONFIG, PLAY_JSON)
libraryDependencies ++= Seq(SCALA_TEST, LOG).flatten

excludeDependencies ++= excludes
dependencyOverrides ++= overrides
