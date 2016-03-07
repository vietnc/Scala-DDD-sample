package sep.com.bbs.application.services

import java.security.MessageDigest
import javax.inject.Inject

import play.api.mvc._
import sep.com.bbs.domain.model.user.{PassWord, UserRepository}
import sep.com.bbs.infra.util.{BbsSecure, BbsLog}

import scala.util.Try

class AuthService @Inject() (userRepository: UserRepository) {

  def validate(email: String, rawPassword: String): Try[Option[Boolean]] = {
    userRepository.findByEmail(email).map(
      _.map{ user =>
        PassWord.fromRaw(rawPassword) == user.password
      }
    )
  }
}

