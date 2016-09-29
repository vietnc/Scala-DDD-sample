package common

import com.typesafe.config.ConfigFactory
import scalikejdbc.ConnectionPool

trait DBSetting {
  DBSetting.initialize()
}

object DBSetting {
  private var isInitialized = false
  val conf = ConfigFactory.load()

  def initialize(): Unit = this.synchronized {
    if (isInitialized) return
    Class.forName("com.mysql.jdbc.Driver")
    ConnectionPool.singleton(conf.getString("db.test.url"), conf.getString("db.test.username"), conf.getString("db.test.password"))
    isInitialized = true
  }

}