package sep.com.bbs.application.services

import sep.com.bbs.domain.model.article.{ArticleRepositoryImpl, Article}
import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared.ArticleID
import sep.com.bbs.infra.dto.ArticleDTO
import sep.com.bbs.infra.util.BbsLog
import scala.util.{Try, Success, Failure}
/**
 * communicate with Domain layer
 */
object ArticleService {

  def getListArticle():List[ArticleDTO] = {

      ArticleRepositoryImpl.resolveAll()
      match{
        case Success(listDTO) => listDTO.map(ar => ArticleDomainService.getDTO(ar))
        case Failure(e) =>
          BbsLog.debug("[Warning][ArticleService.getListArticle] fail: "+ e.getMessage )
          List()
      }
  }

  def viewArticle(id: String):Option[ArticleDTO] = {

      ArticleRepositoryImpl.resolveById(ArticleID(id))
      match{
      case Success(Some(articleDTO)) => Some(ArticleDomainService.getDTO(articleDTO))
      case Failure(e) =>
        BbsLog.debug(s"[Warning][ArticleService.viewArticle({id})] fail:" +  e.getMessage )
        None
      }
  }
  def saveArticle(dto: ArticleDTO) : Boolean = {

    ArticleRepositoryImpl.store(ArticleDomainService.loadDTO(dto))
    match{
      case Success(isOk) => isOk
      case Failure(e) =>
        BbsLog.debug(s"[Warning][ArticleService.saveArticle({dto})] fail:" +  e.getMessage )
        false
    }
  }
}
