package sep.com.bbs.domain.model.article

import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.ArticleDAO

/**
 * Created by Viet on 2/24/2016.
 */

object ArticleRepositoryImpl extends ArticleRepository{
  def resolveById(id: ArticleID): Option[Article] = {
    Some(ArticleDomainService.loadDTO(ArticleDAO.findById(id.value)))
  }

  def resolveAll(): List[Article] = {
    ArticleDAO.findAll().map(articleInfo => ArticleDomainService.loadDTO(articleInfo))
  }

  def store(article: Article) = {
    ArticleDAO.save(ArticleDomainService.getDTO(article))
  }
}
