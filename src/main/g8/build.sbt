import sbt.Keys.scalacOptions
// global
scalaVersion in Global := "$scala_version$"
organization in Global := "$organization_name$"

scalacOptions in Global ++= Seq("-unchecked", "-deprecation", "-feature")
resolvers in Global += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
resolvers in Global += Resolver.url("ooon ivy repo", url("https://repo.ooon.me/release"))(
  Resolver.ivyStylePatterns)
externalResolvers in Global := Resolver.combineDefaultResolvers(resolvers.value.toVector,
  mavenCentral = true)

libraryDependencies in Global ++= Seq(base, scalatest)
libraryDependencies in Global ++= log
excludeDependencies in Global ++= excludes
dependencyOverrides in Global ++= overrides

scmInfo in Global := Some(
  ScmInfo(url("$project_url$"), "$git_url$"))
git.remoteRepo in Global := scmInfo.value.get.connection

cancelable in Global := true
//

lazy val root = (project in file("."))
  .settings(
    moduleName    := "$name$",
    name          := "$name$",
    sourcesInBase := false
  )

lazy val codes = (project in file("codes"))
  .settings(
    moduleName := "codes",
    name       := "$name$ - Code Sources",
    libraryDependencies ++= Seq().flatten,
    libraryDependencies ++= Seq(),
    scalacOptions in (Compile, doc) ++= Seq(
      "-diagrams",
      "-implicits",
      "-groups",
      "-doc-title",
      (description in root).value,
      "-doc-version",
      scalaVersion.value,
      "-sourcepath",
      baseDirectory.in(LocalRootProject).value.getAbsolutePath,
      "-doc-source-url",
      scmInfo.value.get.browseUrl + "/tree/master€{FILE_PATH}.scala"
    )
  )

val CODES = config("codes")
lazy val docs = (project in file("docs"))
  .enablePlugins(SiteScaladocPlugin, ParadoxSitePlugin, ParadoxMaterialThemePlugin, GhpagesPlugin)
  .settings(
    moduleName := "docs",
    name       := "$name$ - Documents",
    ParadoxMaterialThemePlugin.paradoxMaterialThemeSettings(Paradox),
    previewLaunchBrowser := false,
    previewFixedPort     := Some(9000),
    previewFixedIp       := Some("0.0.0.0"),
    ghpagesNoJekyll      := true,
    excludeFilter in ghpagesCleanSite := new FileFilter {

      def accept(f: File) =
        (ghpagesRepository.value / "CNAME").getCanonicalPath == f.getCanonicalPath
    },
    sourceDirectory in Paradox := sourceDirectory.value / "main" / "paradox",
    paradoxProperties in Paradox ++= Map(
      "scaladoc.base_url"   -> "http://$name$.ooon.me/api/",
      "github.base_url"     -> "$project_url$",
      "snip.build.base_dir" -> baseDirectory.value.getAbsolutePath,
      "snip.github_link"    -> "false"
    ),
    paradoxNavigationDepth in Paradox          := 3,
    sourceDirectory in Paradox in paradoxTheme := sourceDirectory.value / "main" / "paradox" / "_template",
    makeSite                                   := makeSite.dependsOn(paradox in Paradox).value,
    mappings in makeSite in Paradox ++= Seq(file("LICENSE") -> "LICENSE"),
    paradoxMaterialTheme in Paradox ~= {
      _.withColor("red", "teal")
        .withCopyright("© zhaihao")
        .withRepository(uri("$project_url$"))
        .withSocial(uri("$developer_url$"),
          uri("https://twitter.com/zhaihaoooon"),
          uri("https://www.facebook.com/zhaihaome"))
        .withLanguage(java.util.Locale.CHINESE)
        .withCustomStylesheet("assets/custom.css")
    },
    autoAPIMappings := true,
    SiteScaladocPlugin
      .scaladocSettings(CODES, mappings in (Compile, packageDoc) in codes, "api/"),
  )
