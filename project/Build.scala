import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "kudos"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    "com.typesafe.slick" % "slick_2.10" % "1.0.0",
    "org.scalatest" % "scalatest_2.10.0" % "2.0.M5" % "test",
    "org.scala-lang" % "scala-actors" % "2.10.0" % "test",
    "com.h2database" % "h2" % "1.3.166"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
