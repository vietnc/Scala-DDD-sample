name := "BBS2"

version := "1.0"

lazy val `bbs2` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  "org.scalikejdbc" %% "scalikejdbc" % "2.2.9",
  "org.scalikejdbc" %% "scalikejdbc-test" % "2.2.9" % "test",
  "mysql" % "mysql-connector-java" % "5.1.24",
  cache,
  "com.typesafe.play" %% "play-mailer" % "3.0.1",
  "com.github.tototoshi" %% "play-flyway" % "1.2.0",
  "org.scalikejdbc" %% "scalikejdbc-config" % "2.2.9",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.4.2",
  ws
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
