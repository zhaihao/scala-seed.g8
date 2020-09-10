import sbt._
import sbtrelease.ReleaseStateTransformations._

import scala.language.postfixOps
import scala.sys.process._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  setNextVersion,
  commitNextVersion
)

releaseCrossBuild           := true
releaseIgnoreUntrackedFiles := true

releaseTagComment    := s"BUILD: releasing ${(version in ThisBuild).value}"
releaseCommitMessage := s"BUILD: prepare version ${(version in ThisBuild).value}"


