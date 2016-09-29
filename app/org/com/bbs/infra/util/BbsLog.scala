package org.com.bbs.infra.util

import play.api.Logger


object BbsLog {

  def debug(message: String) ={
    Logger.debug("[Warning]" + message)
  }
  def error(message: String) = {
    Logger.error("[Error]" + message)
  }

}
