package sep.com.bbs.infra.util

import java.security.MessageDigest


object BbsSecure {
  def hash(password: String): String ={
    val digest = MessageDigest.getInstance("MD5")
    digest.digest(password.getBytes).map("%02x".format(_)).mkString
  }

}
