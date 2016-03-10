package sep.com.bbs.application

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.Action
import sep.com.bbs.application.forms.{SignInForm, LoginForm}
import sep.com.bbs.application.services.{Secured, AuthService, UserService}
import sep.com.bbs.domain.model.user.PassWord
import sep.com.bbs.domain.shared.ID
import sep.com.bbs.infra.dto.{UserDTO, ArticleDTO}
import sep.com.bbs.infra.util._

import scala.util.{Failure, Success}


class UserController @Inject()(authService: AuthService, userService:UserService)  extends BaseController with Secured{

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
          case Success(Some(true)) =>
            Ok("Login successful").withSession("email" -> loginData.email)
          case Success(Some(false)) =>
              BbsLog.debug(s"[Warning] password failed with ($loginData.email,$loginData.password) ")
              Unauthorized("password failed ")
          case Success(None) =>
            //email is not existed
            Unauthorized("maybe email is not existed")
          case Failure(e) =>
            internalServerError("login", new Exception("login error with " + loginData.email))
        }
      }
    )
    }

  def signin() = Action{
    implicit request =>

      SignInForm.form.bindFromRequest().fold(
        formWithErrors => {
          // binding failure
          BbsLog.error("Form Binding for Signin:" + formWithErrors)
          BadRequest("fail to binding with signin form, please try to validate your input")
        },
        signInData => {
          // binding success
          userService.checkUserExisted(signInData.email).map(
            isExisted => if(isExisted != true){
              val userDto = UserDTO(ID.createUID(), signInData.email, PassWord.fromRaw(signInData.password).hashed)
              userService.saveUser(userDto)
              Ok("saved success")
            }else{
              BadRequest("User existed")
            }).getOrElse(
              internalServerError("checkUserExisted",new Exception("Failed to check User existed"))
          )
        }
          )
        }


}

