
package controllers

import play.api._
import play.api.mvc._
import sep.com.bbs.application.services.ArticleService
import play.api.libs.json.Json

object ArticleController extends Controller {

  def getAll() = Action{request =>
    val articles = ArticleService.getListArticle()
    Ok(Json.toJson(articles))
  }

  def getById(id: String) = Action{
    request =>
      val article = ArticleService.viewArticle(id)
      Ok(Json.toJson(article))
  }

}