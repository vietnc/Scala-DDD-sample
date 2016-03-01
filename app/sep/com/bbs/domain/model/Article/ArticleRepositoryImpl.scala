package sep.com.bbs.domain.model.article

import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.ArticleDAO

/**
 * Created by Viet on 2/24/2016.
 */

object ArticleRepositoryImpl extends ArticleRepository{
  def resolveById(id: ArticleID): Option[Article] = {
    ArticleDAO.findById(id.value) match {
      case Some(dto) => Some(ArticleDomainService.loadDTO(dto))
      case _ => None
    }

  }

  def resolveAll(): List[Article] = {
    ArticleDAO.findAll().map(articleInfo => ArticleDomainService.loadDTO(articleInfo))
  }

  def store(article: Article) = {
    ArticleDAO.save(ArticleDomainService.getDTO(article))
  }
}
