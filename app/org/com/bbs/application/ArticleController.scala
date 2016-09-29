package org.com.bbs.application

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import org.com.bbs.application.forms.ArticleForm
import org.com.bbs.application.services.ArticleService
import org.com.bbs.domain.shared.ID
import org.com.bbs.infra.dto.ArticleDTO
import org.com.bbs.infra.util._

import scala.util.{Success,Failure}

import play.api.data._
import play.api.data.Forms._


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
    implicit request =>
      val loginedEmail = request.session.get("email").getOrElse("")

      ArticleForm.form.bindFromRequest.fold(
        formWithErrors => {
          // binding failure
          BbsLog.error("Form Binding for saveArticle:" + formWithErrors)
          BadRequest("fail to binding with form, please try to validate your input")
        },
        articleData => {
          // binding success, you get the actual value. */
          val author = if(loginedEmail != "") loginedEmail
                       else  articleData.email

          val dto = ArticleDTO(
            ID.createUID(),articleData.title, articleData.content,author , DateTime.toString(DateTime.getDate()))

          articleService.saveArticle(dto) match{
            case Success(isOk) => Ok(Json.toJson(isOk))
            case Failure(e) =>
              internalServerError("saveArticle", e)
          }
        }
      )

  }

}