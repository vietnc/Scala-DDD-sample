package org.com.bbs.domain.service

import org.com.bbs.domain.model.article.{Author, Article}
import org.com.bbs.domain.shared.ArticleID
import org.com.bbs.infra.dto.ArticleDTO
import org.com.bbs.infra.util.DateTime

/**
 * transform DTO & Domain object.
 */

object ArticleDomainService extends ConvertEntity[ArticleDTO, Article] {

  def toEntity(dto : ArticleDTO): Article ={
    Article(ArticleID(dto.id),
      dto.title,
      dto.content,
      Author(dto.email),DateTime.getDate(dto.createdDate))
  }

  def toDataTrasferObject(ar: Article): ArticleDTO ={
    ArticleDTO(ar.id.value,
      ar.title, ar.content,
      ar.author.email,
      DateTime.toString(ar.createdDate))
  }
}