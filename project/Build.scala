import sbt.Keys._
import sbt._

object HelloBuild extends Build {
  lazy val resolverSeq = Seq(
    "Artifactory-Snapshot" at "http://repo.in.xxx.com/artifactory/libs-snapshot-local/",
    "Artifactory-Release" at "http://repo.in.xxx.com/artifactory/libs-release-local/",
    "Cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos"
  )

  lazy val commonLibSeq = Seq(
    "org.scalatest" %% "scalatest" % "1.9.1" % "test",
    "com.zhihu.data.ip" % "IpInfo" % "0.4.7"
  )

  lazy val helloLibSeq = Seq()

  lazy val barLibSeq = Seq()

  lazy val fooLibSeq = Seq()

  lazy val commonSettings = Seq(
    organization := "com.zhihu.data",
    version := "1.0",
    scalaVersion := "2.10.6",
    ivyScala := ivyScala.value map {
      _.copy(overrideScalaVersion = true)
    },
    resolvers ++= resolverSeq
  )

  // aggregate: running a task on the aggregate project will also run it on the aggregated projects.
  // dependsOn: a project depends on code in another project.
  // without dependsOn, you'll get a compiler error: "object bar is not a member of package
  // com.alvinalexander".
  lazy val root = Project(id = "hello",
    base = file(".")) aggregate foo dependsOn foo
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= commonLibSeq,
      libraryDependencies ++= helloLibSeq
    )

  // sub-project in the foo subdirectory
  lazy val foo = Project(id = "hello-foo",
    base = file("foo")) aggregate bar dependsOn bar
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= commonLibSeq,
      libraryDependencies ++= fooLibSeq
    )

  // sub-project in the bar subdirectory
  lazy val bar = Project(id = "hello-bar",
    base = file("bar"))
    .settings(commonSettings: _*)
    .settings(
      libraryDependencies ++= commonLibSeq,
      libraryDependencies ++= barLibSeq
    )
}
