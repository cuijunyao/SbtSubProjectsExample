import sbt.Keys._

lazy val resolverSeq = Seq(
  "Artifactory-Snapshot" at "http://repo.in.zhihu.com/artifactory/libs-snapshot-local/",
  "Artifactory-Release" at "http://repo.in.zhihu.com/artifactory/libs-release-local/",
  "Cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos"
)

lazy val libSeq = Seq(
  "org.scalacheck" %% "scalacheck" % "1.12.4" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.zhihu.data.ip" % "IpInfo" % "0.4.7"
  )

lazy val commonSettings = Seq(
  organization := "com.zhihu.data",
  name := "sub-project-foo",
  version := "1.0",
  scalaVersion := "2.10.6",
  resolvers ++= resolverSeq
)