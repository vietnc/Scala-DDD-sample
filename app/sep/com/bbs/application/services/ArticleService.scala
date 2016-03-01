package sep.com.bbs.application.services

import sep.com.bbs.domain.model.article.{ArticleRepositoryImpl, Article}
import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared.ArticleID
import sep.com.bbs.infra.dto.ArticleDTO

/**
 * communicate with Domain layer
 */
object ArticleService {

  def getListArticle():List[ArticleDTO] = {
    ArticleRepositoryImpl.resolveAll().map(ar => ArticleDomainService.getDTO(ar))
  }

  def viewArticle(id: String):Option[ArticleDTO] = {
    ArticleRepositoryImpl.resolveById(ArticleID(id)) match{
      case Some(ar) => Some(ArticleDomainService.getDTO(ar))
      case _ => None
    }
  }
  def saveArticle(dto: ArticleDTO) : Boolean = {
    ArticleRepositoryImpl.store(ArticleDomainService.loadDTO(dto))
  }
}
