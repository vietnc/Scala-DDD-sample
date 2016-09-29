package common

import com.typesafe.config.ConfigFactory
import play.api.db.{Database, Databases}
import play.api.inject._
import play.api.inject.guice.GuiceInjectorBuilder

trait TestDBHelper {
  val conf = ConfigFactory.load()
  private val dbUrl = conf.getString("db.test.url")

  private val dbConfig = Map(
    "driver" -> "com.mysql.jdbc.Driver",
    "url" -> dbUrl,
    "username" -> conf.getString("db.test.username"),
    "password" -> conf.getString("db.test.password")
  )

  val testDb = Databases(
    driver = dbConfig("driver"),
    url = dbConfig("url"),
    name = "test",
    config = Map(
      "username" -> dbConfig("username"),
      "password" -> dbConfig("password")
    )
  )

  val guice = new GuiceInjectorBuilder()
    .overrides(bind[Database].toInstance(testDb))
    .build()
}
