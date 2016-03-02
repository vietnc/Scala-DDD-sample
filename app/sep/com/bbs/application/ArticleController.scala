package sep.com.bbs.application

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import sep.com.bbs.application.services.ArticleService
import sep.com.bbs.domain.shared.ID
import sep.com.bbs.infra.dto.ArticleDTO
import sep.com.bbs.infra.util.{BbsLog, DateTime}

import scala.util.{Success,Failure}

class ArticleController @Inject() (articleService: ArticleService)  extends BaseController{

  def list() = Action{
    request =>
      articleService.getListArticle() match{
        case Success(listDTO) => Ok(Json.toJson(listDTO))
        case Failure(e) =>
          internalServerError("Article.list", e)
      }
    }


  def getById(id: String) = Action{
    request =>
      articleService.viewArticle(id) match{
        case Success(Some(articleDTO)) => Ok(Json.toJson(articleDTO))
        case Success(None) => notFoundException(s"Article.getById({id})", "no result for this ID")
        case Failure(e) =>
          internalServerError(s"Article.getById({id})", e)
      }

  }

  def saveArticle = Action{
    request =>
      //todo: extract from request
      val dto = ArticleDTO(
        ID.createUID(), "title", "content", "author", DateTime.getDate().toString)

    articleService.saveArticle(dto)
    match{
        case Success(isOk) => Ok(Json.toJson(isOk))
        case Failure(e) =>
          internalServerError("saveArticle", e)
      }
  }

}
