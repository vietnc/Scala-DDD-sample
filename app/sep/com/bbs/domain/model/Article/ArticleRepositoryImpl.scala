package sep.com.bbs.domain.model.article

import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.ArticleDAO

import scala.util.Try

object ArticleRepositoryImpl extends ArticleRepository{

  def resolveById(id: ArticleID): Try[Option[Article]] = {
    Try(
      ArticleDAO.findById(id.value) match {
      case Some(dto) => Some(ArticleDomainService.loadDTO(dto))
      case _ => None
    })

  }

  def resolveAll(): Try[List[Article]] = {
    Try(
      ArticleDAO.findAll().map(articleInfo => ArticleDomainService.loadDTO(articleInfo)))
  }

  def store(article: Article): Try[Boolean] = {
    Try(
      ArticleDAO.save(ArticleDomainService.getDTO(article)))
  }
}
