
package controllers

import play.api._
import play.api.mvc._
import sep.com.bbs.application.json.ArticleJsonSupport._
import sep.com.bbs.application.services.ArticleService
import play.api.libs.json.Json

object ArticleController extends Controller {

  def list() = Action{
    Ok(views.html.list(ArticleService.getListArticle()))
  }

  def getAll() = Action{request =>
    val articles = ArticleService.getListArticle()
    Ok(Json.toJson(articles))
  }

}