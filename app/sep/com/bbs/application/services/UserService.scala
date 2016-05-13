package sep.com.bbs.application.services

import java.sql.SQLException
import javax.inject.Inject

import sep.com.bbs.domain.model.article._
import sep.com.bbs.domain.model.user.UserRepository
import sep.com.bbs.domain.service.UserDomainService
import sep.com.bbs.domain.shared.UserID
import sep.com.bbs.infra.dto.UserDTO
import scala.util.{Try, Success, Failure}
/**
  * communicate with Domain layer
  */
class UserService @Inject()(userRepo: UserRepository) {


  def checkUserExisted(email: String):Try[Boolean] = {
    userRepo.findByEmail(email).map(u => u.getOrElse(false) != false )
  }

  def saveUser(dto: UserDTO) : Try[Boolean] = {
    userRepo.store(UserDomainService.loadDTO(dto))
  }
}
