package sep.com.bbs.application.services

import java.sql.SQLException
import javax.inject.Inject

import scalikejdbc.TxBoundary.Exception
import sep.com.bbs.domain.model.article._
import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared.ArticleID
import sep.com.bbs.infra.dto.ArticleDTO
import sep.com.bbs.infra.util.BbsLog
import scala.util.{Try, Success, Failure}
/**
 * communicate with Domain layer
 */
class ArticleService @Inject()(articleRepo: ArticleRepository) {

  def getListArticle():Try[List[ArticleDTO]] = {
    articleRepo.resolveAll().map(_.map(ArticleDomainService.getDTO))
  }

  def viewArticle(id: String):Try[Option[ArticleDTO]] = {
    articleRepo.resolveById(ArticleID(id)).map(_.map(ArticleDomainService.getDTO))
  }

  def saveArticle(dto: ArticleDTO) : Try[Boolean] = {
    articleRepo.store(ArticleDomainService.loadDTO(dto))
  }
}
