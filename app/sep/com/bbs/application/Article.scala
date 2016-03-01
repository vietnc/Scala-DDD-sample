package sep.com.bbs.application

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import sep.com.bbs.application.services.ArticleService
import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.dto._
import sep.com.bbs.infra.util.DateTime

/**
  *
  */
class Article  extends Controller{


  def getAll() = Action{request =>
    val articles = ArticleService.getListArticle()
    Ok(Json.toJson(articles))
  }

  def getById(id: String) = Action{
    request =>
      val article = ArticleService.viewArticle(id)
      Ok(Json.toJson(article))
  }

  def saveArticle() = Action{
    request =>
      val dto = ArticleDTO(
        ID.createUID(),
        "title",
        "content",
        "author",
        DateTime.getDate().toString)

    Ok(Json.toJson(ArticleService.saveArticle(dto)))

  }
}
