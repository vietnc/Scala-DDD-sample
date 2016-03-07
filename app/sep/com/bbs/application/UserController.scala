package sep.com.bbs.application

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.Action
import sep.com.bbs.application.forms.LoginForm
import sep.com.bbs.application.services.{Secured, AuthService, ArticleService}
import sep.com.bbs.domain.shared.ID
import sep.com.bbs.infra.dto.ArticleDTO
import sep.com.bbs.infra.util._

import scala.util.{Failure, Success}


class UserController @Inject()(authService: AuthService)  extends BaseController with Secured{

  def login() = Action{
    implicit request =>
      
      LoginForm.form.bindFromRequest().fold(
      formWithErrors => {
        // binding failure
        BbsLog.error("Form Binding for login:" + formWithErrors)
        BadRequest("fail to binding with login form, please try to validate your input")
      },
      loginData => {
        // binding success
        authService.validate(loginData.email, loginData.password) match{
          case Success(Some(isOk)) =>
            if(isOk) Ok("Login successful").withSession("email" -> loginData.email)
            else {
              BbsLog.debug(s"[Warning] password failed with ($loginData.email,$loginData.password) ")
              Unauthorized
            }
          case Success(None) =>
            //email is not existed
            Unauthorized
          case Failure(e) =>
            internalServerError("login", new Exception("login error with " + loginData.email))
        }
      }
    )
    }



}

