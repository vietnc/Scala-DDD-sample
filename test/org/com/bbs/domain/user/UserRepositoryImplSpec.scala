package org.com.bbs.domain.user

import common.FakeAppHelper
import org.specs2.mock.Mockito
import play.api.test.PlaySpecification
import org.com.bbs.domain.model.user.UserRepositoryImpl
import org.com.bbs.domain.service.UserDomainService
import org.com.bbs.infra.UserDAO
import org.com.bbs.infra.dto.UserDTO

/**
  * Created by Viet on 3/7/2016.
  */
class UserRepositoryImplSpec extends PlaySpecification with FakeAppHelper with Mockito{

  val mockDAO = mock[UserDAO]
  val dtoUserTest = UserDTO("1","vietngc@gmail.com","202cb962ac59075b964b07152d234b70")

  "UserRepository " should{
    "findByEmail: " in {

      "existed email in db return User object" in {
        (mockDAO.findByEmail(dtoUserTest.email)) returns (Some(dtoUserTest))

        val repo = new UserRepositoryImpl(mockDAO)
        repo.findByEmail(dtoUserTest.email).get.getOrElse(None) must equalTo(UserDomainService.toEntity(dtoUserTest))
      }
      "not existed email in db " in {
        (mockDAO.findByEmail(dtoUserTest.email)) returns (None)
        val repo = new UserRepositoryImpl(mockDAO)
        repo.findByEmail(dtoUserTest.email).get must equalTo(None)
      }
    }

  }
}
