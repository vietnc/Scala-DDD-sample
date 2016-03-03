package sep.com.bbs.application.forms

import play.api.data.Form
import play.api.data.Forms._
import sep.com.bbs.infra.dto.ArticleDTO

object ArticleForm {
  val form = Form(
    mapping(
      "id" -> text,
      "title" -> text,
      "content" -> text,
      "email" ->email,
      "createdDate"  -> text
    )(ArticleDTO.apply)(ArticleDTO.unapply)
  )
}
