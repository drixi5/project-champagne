name := """play-project"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
	
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.apache.commons" % "commons-email" % "1.3",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.typesafe.play" % "play-mailer_2.11" % "2.4.0",
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)


scalacOptions ++= Seq("-encoding", "UTF-8")