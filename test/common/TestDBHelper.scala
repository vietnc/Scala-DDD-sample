package common

import play.api.db.{Database, Databases}
import play.api.inject._
import play.api.inject.guice.GuiceInjectorBuilder

trait TestDBHelper {
  private val dbUrl =
      "jdbc:mysql://localhost/test?characterEncoding=UTF-8"

  private val dbConfig = Map(
    "driver" -> "com.mysql.jdbc.Driver",
    "url" -> dbUrl,
    "username" -> "test",
    "password" -> "testpassword"
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
