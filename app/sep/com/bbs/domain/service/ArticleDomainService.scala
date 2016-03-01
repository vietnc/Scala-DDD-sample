package sep.com.bbs.domain.service

import sep.com.bbs.domain.model.article.{Author, Article}
import sep.com.bbs.domain.shared.ArticleID
import sep.com.bbs.infra.dto.ArticleDTO
import sep.com.bbs.infra.util.DateTime

/**
 * transform DTO & Domain object.
 */

object ArticleDomainService extends TransferDTO[ArticleDTO, Article] {

  def loadDTO(dto : ArticleDTO): Article ={
    Article(ArticleID(dto.id),
      dto.title,
      dto.content,
      Author(dto.email),DateTime.getDate(dto.createdDate))
  }

  def getDTO(ar: Article): ArticleDTO ={
    ArticleDTO(ar.id.value,
      ar.title, ar.content,
      ar.author.email,
      DateTime.toString(ar.createdDate))
  }
}