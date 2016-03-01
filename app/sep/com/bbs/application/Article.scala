package sep.com.bbs.application

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import sep.com.bbs.application.services.ArticleService
import sep.com.bbs.domain.model.article.ArticleRepositoryImpl
import sep.com.bbs.domain.service.ArticleDomainService

/**
  *
  */
object Article  extends Controller{

  def viewArticle() = Action{request =>
    val articles = ArticleService.getListArticle()
    Ok(Json.toJson(articles))
  }

  def getById(id: String) = Action{
    request =>
      val article = ArticleService.viewArticle(id)
      Ok(Json.toJson(article))
  }
}
