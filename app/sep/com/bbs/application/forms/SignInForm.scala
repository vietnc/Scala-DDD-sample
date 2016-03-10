package sep.com.bbs.application.forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

object SignInForm{
  case class UserSignInPost(email: String, password: String)
  implicit val w = Json.writes[UserSignInPost]
  implicit val r = Json.reads[UserSignInPost]

  val form = Form(
    mapping(
      "email" -> text,
      "password" -> text
    )(UserSignInPost.apply)(UserSignInPost.unapply)
  )
}
