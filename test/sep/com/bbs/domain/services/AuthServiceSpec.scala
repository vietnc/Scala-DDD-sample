package sep.com.bbs.domain.services

import common.FakeAppHelper
import org.specs2.mock.Mockito
import play.api.test.PlaySpecification
import sep.com.bbs.application.services.AuthService
import sep.com.bbs.domain.model.user.UserRepository

/**
  * Created by Viet on 3/7/2016.
  */
class AuthServiceSpec  extends PlaySpecification with FakeAppHelper with Mockito{
  {
    val userRepo =  mock[UserRepository]

    "hash md5" in {
      val auth = new AuthService(userRepo)
      auth.hash("123") must equalTo("202cb962ac59075b964b07152d234b70")
    }
  }

}
