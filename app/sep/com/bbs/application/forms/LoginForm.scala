package sep.com.bbs.application.forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

object LoginForm {
  case class UserLoginPost(email: String, password: String)
  implicit val w = Json.writes[UserLoginPost]
  implicit val r = Json.reads[UserLoginPost]

  val form = Form(
    mapping(
      "email" -> text,
      "password" -> text
    )(UserLoginPost.apply)(UserLoginPost.unapply)
  )
}
