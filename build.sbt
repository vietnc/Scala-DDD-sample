name := "BBS"

version := "1.0"

lazy val `bbs` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.37",
  "org.scalikejdbc" %% "scalikejdbc" % "2.3.2",
  "org.scalikejdbc" %% "scalikejdbc-config" % "2.3.2",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.4.3",
  "org.scalikejdbc" %% "scalikejdbc-play-fixture" % "2.4.1",
  "org.scalikejdbc" %% "scalikejdbc-test" % "2.3.5" % "test",
  "org.scalikejdbc" %% "scalikejdbc-play-dbapi-adapter" % "2.4.0",
  "com.h2database" % "h2" % "1.4.191",
  "ch.qos.logback" % "logback-classic" % "1.1.3"
)

routesGenerator := InjectedRoutesGenerator

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/evolutions/test" )
