package org.com.bbs.application

import play.api.libs.json.Json
import play.api.mvc.Controller
import org.com.bbs.application.services.AuthService
import org.com.bbs.infra.util.BbsLog

import scala.util.parsing.json.JSONObject

trait BaseController extends Controller {

  def notFoundException(fName: String, msg: String) = {
    BbsLog.debug(s"[Warning][{fName}] not found:" +  msg)
    NotFound(JSONObject(Map("error" -> msg)).toString())
  }

  def internalServerError(fName: String, e: Throwable) = {
    BbsLog.debug(s"[Exception][{fName}] failed:" +  e.getMessage )
    InternalServerError(JSONObject(Map("exception" -> e.getMessage)).toString())
  }

}
