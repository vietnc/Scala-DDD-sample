package sep.com.bbs.domain.model.article

import javax.inject._

import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.ArticleDAO

import scala.util.Try
@Singleton
class ArticleRepositoryImpl @Inject()(articleDAO: ArticleDAO) extends ArticleRepository{

  def resolveById(id: ArticleID): Try[Option[Article]] = Try {
    articleDAO.findById(id.value) match {
      case Some(dto) => Some(ArticleDomainService.loadDTO(dto))
      case _ => None
    }
  }

  def resolveAll(): Try[List[Article]] = Try {
    articleDAO.findAll().map(articleInfo => ArticleDomainService.loadDTO(articleInfo))
  }

  def store(article: Article): Try[Boolean] = Try {
    articleDAO.save(ArticleDomainService.getDTO(article))
  }
}
