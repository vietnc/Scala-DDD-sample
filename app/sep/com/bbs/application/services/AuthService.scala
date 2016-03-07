package sep.com.bbs.application.services

import java.security.MessageDigest
import javax.inject.Inject

import play.api.mvc._
import sep.com.bbs.domain.model.user.UserRepository
import sep.com.bbs.infra.util.{BbsSecure, BbsLog}

import scala.util.Try

class AuthService @Inject() (userRepository: UserRepository) {

  def validate(email: String, password: String): Try[Option[Boolean]] = {
    userRepository.findByEmail(email).map(
      _ match{
        case Some(user) => Some(BbsSecure.hash(password) == user.password)
        case None => None
      }
      )
  }
}

