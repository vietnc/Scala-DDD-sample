package sep.com.bbs.domain.impl

import sep.com.bbs.domain.model.article.{Article, ArticleRepository}
import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.ArticleDAO
import sep.com.bbs.infra.dto.ArticleDTO

/**
 * Created by Viet on 2/24/2016.
 */

object ArticleRepository extends ArticleRepository{
  def resolveByById(id: ArticleID): Option[Article] = {
    Some(ArticleDomainService.loadDTO(ArticleDAO.findById(id.guid)))
  }

  def resolveAll(): List[Article] = {
    ArticleDAO.findAll().map(articleInfo => ArticleDomainService.loadDTO(articleInfo))
  }

  def store(article: Article) = {
    ArticleDAO.save(ArticleDomainService.getDTO(article))
  }
}
