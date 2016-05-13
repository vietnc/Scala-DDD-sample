package controllers

import play.api.mvc._

class HtmlController extends Controller{

  def showArticleListScreen: Action[AnyContent] = Action{
    Ok(views.html.article.list())
  }

  def viewArticle(id: String): Action[AnyContent] = Action{
    Ok(views.html.article.detail(id))
  }
  def signIn: Action[AnyContent] = Action{
    Ok(views.html.user.signin()  )
  }
}
