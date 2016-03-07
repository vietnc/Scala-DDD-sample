package common

import scalikejdbc.ConnectionPool

trait DBSetting {
  DBSetting.initialize()
}

object DBSetting {
  private var isInitialized = false

  def initialize(): Unit = this.synchronized {
    if (isInitialized) return
    Class.forName("com.mysql.jdbc.Driver")
    ConnectionPool.singleton("jdbc:mysql://localhost/test?characterEncoding=UTF-8", "root", "")
    isInitialized = true
  }

}