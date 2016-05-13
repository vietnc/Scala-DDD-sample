package sep.com.bbs.application

import java.sql.SQLException

import common.FakeAppHelper
import org.specs2.mock.Mockito
import play.api.inject.guice.GuiceInjectorBuilder
import play.api.test.{FakeRequest, PlaySpecification, ResultExtractors, WithApplication}
import sep.com.bbs.application.services.{UserService, AuthService}
import sep.com.bbs.domain.model.user.PassWord
import sep.com.bbs.domain.shared.ID
import sep.com.bbs.infra.dto.UserDTO
import scala.util.{Try, Success, Failure}

class UserControllerSpec extends PlaySpecification with FakeAppHelper with ResultExtractors with Mockito {
  running(fakeApp) {
    val guice = new GuiceInjectorBuilder().build()
    val controller = guice.instanceOf[UserController]
    val authService = mock[AuthService]
    val userService = mock[UserService]
    "UserController " should {
      "login()" in {
        " user and pass 's correct " in new WithApplication(fakeApp) {

          val request = FakeRequest(POST, "/user/login")
            .withFormUrlEncodedBody(("email","vietngc@gmail.com"),("password","123"))

          val result = call(controller.login, request)
          status(result) must beEqualTo(OK)
        }
        " user or pass 's incorrect " in new WithApplication(fakeApp) {

          val request = FakeRequest(POST, "/user/login")
            .withFormUrlEncodedBody(("email","vietngc2@gmail.com"),("password","123"))

          val result = call(controller.login, request)
          status(result) must beEqualTo(UNAUTHORIZED)
        }
        "there are exception internal" in new WithApplication(fakeApp) {

          val request = FakeRequest(POST, "/user/login")
            .withFormUrlEncodedBody(("email","vietngc2@gmail.com"),("password","123"))
          authService.validate("vietngc2@gmail.com","123") returns Failure(new Exception("Db not existed"))

          val controller =  new UserController(authService, userService)
          val result = call(controller.login, request)

          status(result) must beEqualTo(INTERNAL_SERVER_ERROR)
          contentAsString(result) must contain("exception")
        }
      }

      "signin()" in {
        " with new user " in new WithApplication(fakeApp) {

          val request = FakeRequest(POST, "/user/signin")
            .withFormUrlEncodedBody(("email",scala.util.Random.nextInt(10000) + "rand@gmail.com"),("password","123"))

          val result = call(controller.signin, request)
          status(result) must beEqualTo(OK)
        }
        " user email existed " in new WithApplication(fakeApp) {

          val request = FakeRequest(POST, "/user/signin")
            .withFormUrlEncodedBody(("email","vietngc@gmail.com"),("password","123"))

          val result = call(controller.login, request)
          status(result) must beEqualTo(OK)
        }
        "there are exception when checkUserExisted" in new WithApplication(fakeApp) {

          val request = FakeRequest(POST, "/user/signin")
            .withFormUrlEncodedBody(("email","vietngc2@gmail.com"),("password","123"))
          userService.checkUserExisted("vietngc@gmail.com") returns Failure(new Exception("Databse Corrupt"))

          val controller =  new UserController(authService, userService)
          val result = call(controller.signin, request)

          status(result) must beEqualTo(INTERNAL_SERVER_ERROR)
          contentAsString(result) must contain("Unknow Error")
        }
        "SQL exception when saveUser" in new WithApplication(fakeApp) {

          val dto =  UserDTO(ID.createUID(), "vietngc2@gmail.com", PassWord.fromRaw("123").hashed)
          val request = FakeRequest(POST, "/user/signin")
            .withFormUrlEncodedBody(("email",dto.email),("password","123"))
          userService.checkUserExisted(dto.email) returns Success(false)

          userService.saveUser(dto) returns Failure(new SQLException("SQL inserted fail: Db interuption" ))

          val controller =  new UserController(authService, userService)
          val result = call(controller.signin, request)

          status(result) must beEqualTo(INTERNAL_SERVER_ERROR)
          contentAsString(result) must contain("SQL Error:")
        }
        "SQL exception when saveUser" in new WithApplication(fakeApp) {

          val dto =  UserDTO(ID.createUID(), "vietngc2@gmail.com", PassWord.fromRaw("123").hashed)
          val request = FakeRequest(POST, "/user/signin")
            .withFormUrlEncodedBody(("email",dto.email),("password","123"))
          userService.checkUserExisted(dto.email) returns Success(false)

          userService.saveUser(dto) returns Failure(new SQLException("SQL inserted fail: Db interuption" ))

          val controller =  new UserController(authService, userService)
          val result = call(controller.signin, request)

          status(result) must beEqualTo(INTERNAL_SERVER_ERROR)
          contentAsString(result) must contain("SQL Error:")
        }
        "other exception when saveUser" in new WithApplication(fakeApp) {

          val dto =  UserDTO(ID.createUID(), "vietngc2@gmail.com", PassWord.fromRaw("123").hashed)
          val request = FakeRequest(POST, "/user/signin")
            .withFormUrlEncodedBody(("email",dto.email),("password","123"))
          userService.checkUserExisted(dto.email) returns Success(false)

          userService.saveUser(dto) returns Failure(new Exception("any kind of exception" ))

          val controller =  new UserController(authService, userService)
          val result = call(controller.signin, request)

          status(result) must beEqualTo(INTERNAL_SERVER_ERROR)
          contentAsString(result) must contain("Unknow Error")
        }
      }

    }
  }
}
