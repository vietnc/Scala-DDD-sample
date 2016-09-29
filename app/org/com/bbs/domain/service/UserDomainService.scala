package org.com.bbs.domain.service

import org.com.bbs.domain.model.article.{Article, Author}
import org.com.bbs.domain.model.user.{PassWord, User}
import org.com.bbs.domain.shared.{UserID, ArticleID}
import org.com.bbs.infra.dto.{UserDTO, ArticleDTO}
import org.com.bbs.infra.util.DateTime

/**
 * transform DTO & Domain object.
 */

object UserDomainService extends ConvertEntity[UserDTO, User] {

  def toEntity(dto : UserDTO): User ={
    User(UserID(dto.id),
      dto.email,
      PassWord(dto.password))
  }

  def toDataTrasferObject(u: User): UserDTO ={
    UserDTO(u.id.value,
      u.email, u.password.hashed)
  }
}