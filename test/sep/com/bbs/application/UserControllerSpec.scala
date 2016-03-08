package sep.com.bbs.application

import common.FakeAppHelper
import org.specs2.mock.Mockito
import play.api.inject.guice.GuiceInjectorBuilder
import play.api.test.{FakeRequest, PlaySpecification, ResultExtractors, WithApplication}
import sep.com.bbs.application.services.AuthService
import scala.util.{Try, Success, Failure}

class UserControllerSpec extends PlaySpecification with FakeAppHelper with ResultExtractors with Mockito {
  running(fakeApp) {
    val guice = new GuiceInjectorBuilder().build()
    val controller = guice.instanceOf[UserController]
    val authService = mock[AuthService]

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

          val controller =  new UserController(authService)
          val result = call(controller.login, request)

          status(result) must beEqualTo(INTERNAL_SERVER_ERROR)
          contentAsString(result) must contain("exception")
        }
      }

    }
  }
}
