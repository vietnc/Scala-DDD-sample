package sep.com.bbs.domain.impl

import sep.com.bbs.domain.model.Article.{Article, ArticleRepository}
import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.ArticleDAO
import sep.com.bbs.infra.dto.ArticleDTO

/**
 * Created by Viet on 2/24/2016.
 */

object ArticleRepository extends ArticleRepository{
  def findById(id: ArticleID): Option[Article] = {
    val articleInfo: ArticleDTO = ArticleDAO.findById("uniqueID")
    Some(Article.load(articleInfo))
  }

  def findAll(): List[Article] = {
    val articleInfoList = ArticleDAO.findAll()
    articleInfoList.map(articleInfo => Article.load(articleInfo))
  }

  def store(article: Article) = {
    ArticleDAO.save(Article.getDTO(article))
  }
}
