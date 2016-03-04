package sep.com.bbs.application.forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

object ArticleForm {
  case class ArticlePost(title: String, content: String, email: String)
  implicit val w = Json.writes[ArticlePost]
  implicit val r = Json.reads[ArticlePost]

  val form = Form(
    mapping(
      "title" -> text,
      "content" -> text,
      "email" ->email
    )(ArticlePost.apply)(ArticlePost.unapply)
  )
}
