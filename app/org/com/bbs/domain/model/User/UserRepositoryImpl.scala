package org.com.bbs.domain.model.user

import javax.inject.Inject

import org.com.bbs.domain.service.UserDomainService
import org.com.bbs.domain.shared._
import org.com.bbs.infra.UserDAO

import scala.util.Try
class UserRepositoryImpl @Inject()(userDAO: UserDAO)extends UserRepository{

    def findByEmail(email: String): Try[Option[User]] = Try {
      userDAO.findByEmail(email).map(UserDomainService.toEntity)
    }

    def resolveById(id: UserID): Try[Option[User]] = Try {
      None
    }
    def resolveAll(): Try[List[User]] = Try {
      List()
    }

    def store(user: User): Try[Boolean] = Try {
      userDAO.save(UserDomainService.toDataTrasferObject(user))
    }


}
