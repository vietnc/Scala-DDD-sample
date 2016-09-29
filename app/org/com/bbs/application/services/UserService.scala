package org.com.bbs.application.services

import java.sql.SQLException
import javax.inject.Inject

import org.com.bbs.domain.model.article._
import org.com.bbs.domain.model.user.UserRepository
import org.com.bbs.domain.service.UserDomainService
import org.com.bbs.domain.shared.UserID
import org.com.bbs.infra.dto.UserDTO
import scala.util.{Try, Success, Failure}
/**
  * communicate with Domain layer
  */
class UserService @Inject()(userRepo: UserRepository) {


  def checkUserExisted(email: String):Try[Boolean] = {
    userRepo.findByEmail(email).map(u => u.getOrElse(false) != false )
  }

  def saveUser(dto: UserDTO) : Try[Boolean] = {
    userRepo.store(UserDomainService.toEntity(dto))
  }
}
