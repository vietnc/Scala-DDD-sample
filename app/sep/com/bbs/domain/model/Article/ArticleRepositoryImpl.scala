package sep.com.bbs.domain.model.article

import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.ArticleDAO

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

  def store(article: Article): Boolean = {
    ArticleDAO.save(ArticleDomainService.getDTO(article))
  }
}
