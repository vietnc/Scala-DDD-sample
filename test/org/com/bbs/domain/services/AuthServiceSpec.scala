package org.com.bbs.domain.services

import org.com.bbs.domain.shared.UserID
import org.com.bbs.infra.dto.UserDTO

import scala.util.{Failure, Success}
import common.FakeAppHelper
import org.specs2.mock.Mockito
import play.api.test.PlaySpecification
import org.com.bbs.application.services.AuthService
import org.com.bbs.domain.model.user.{PassWord, User, UserRepository}

class AuthServiceSpec  extends PlaySpecification with FakeAppHelper with Mockito{
  {
    val userRepo =  mock[UserRepository]
    val dtoUser  = UserDTO("1","vietngc@gmail.com","202cb962ac59075b964b07152d234b70") //~ pass: 123

    "AuthService" should{
      "validate(email, pass) return true with correct " in{
        userRepo.findByEmail(dtoUser.email) returns Success(Some(User(UserID("1"),dtoUser.email, PassWord(dtoUser.password))))

        val authService = new AuthService(userRepo)
        authService.validate(dtoUser.email, "123").get must equalTo(Some(true))
      }
      "validate(email, pass) return false with wrong password " in{
        userRepo.findByEmail(dtoUser.email) returns Success(Some(User(UserID("1"),dtoUser.email, PassWord(dtoUser.password))))

        val authService = new AuthService(userRepo)
        authService.validate(dtoUser.email, "1234").get must equalTo(Some(false))
      }
      "validate(email, pass) return none with wrong email " in{
        userRepo.findByEmail(dtoUser.email) returns Success(Some(User(UserID("1"),dtoUser.email,PassWord(dtoUser.password))))
        userRepo.findByEmail("vietngc2@gmail") returns Success(None)

        val authService = new AuthService(userRepo)
        authService.validate("vietngc2@gmail", "123") must equalTo(Success(None))
      }
    }
  }

}
