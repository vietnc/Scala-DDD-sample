package org.com.bbs.application.services

import java.sql.SQLException
import javax.inject.Inject

import scalikejdbc.TxBoundary.Exception
import org.com.bbs.domain.model.article._
import org.com.bbs.domain.service.ArticleDomainService
import org.com.bbs.domain.shared.ArticleID
import org.com.bbs.infra.dto.ArticleDTO
import scala.util.{Try, Success, Failure}
/**
 * communicate with Domain layer
 */
class ArticleService @Inject()(articleRepo: ArticleRepository) {

  def getListArticle():Try[List[ArticleDTO]] = {
    articleRepo.resolveAll().map(_.map(ArticleDomainService.toDataTrasferObject))
  }

  def viewArticle(id: String):Try[Option[ArticleDTO]] = {
    articleRepo.resolveById(ArticleID(id)).map(_.map(ArticleDomainService.toDataTrasferObject))
  }

  def saveArticle(dto: ArticleDTO) : Try[Boolean] = {
    articleRepo.store(ArticleDomainService.toEntity(dto))
  }
}
