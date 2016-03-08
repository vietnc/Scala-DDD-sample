package sep.com.bbs.domain.service

import sep.com.bbs.domain.model.article.{Article, Author}
import sep.com.bbs.domain.model.user.{PassWord, User}
import sep.com.bbs.domain.shared.{UserID, ArticleID}
import sep.com.bbs.infra.dto.{UserDTO, ArticleDTO}
import sep.com.bbs.infra.util.DateTime

/**
 * transform DTO & Domain object.
 */

object UserDomainService extends TransferDTO[UserDTO, User] {

  def loadDTO(dto : UserDTO): User ={
    User(UserID(dto.id),
      dto.email,
      PassWord(dto.password))
  }

  def getDTO(u: User): UserDTO ={
    UserDTO(u.id.value,
      u.email, u.password.hashed)
  }
}