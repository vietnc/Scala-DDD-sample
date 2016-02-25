package controllers

import play.api._
import play.api.mvc._
import sep.com.bbs.application.services.ArticleService

object Application extends Controller {

  def list() = Action{
    Ok(views.html.list(ArticleService.getListArticle()))
  }
}