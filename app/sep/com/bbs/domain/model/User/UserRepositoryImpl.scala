package sep.com.bbs.domain.model.user

import javax.inject.Inject

import sep.com.bbs.domain.service.UserDomainService
import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.UserDAO

import scala.util.Try
class UserRepositoryImpl @Inject()(userDAO: UserDAO)extends UserRepository{

    def findByEmail(email: String): Try[Option[User]] = Try {
      userDAO.findByEmail(email).map(UserDomainService.loadDTO)
    }

    def resolveById(id: UserID): Try[Option[User]] = Try {
      None
    }
    def resolveAll(): Try[List[User]] = Try {
      List()
    }

    def store(user: User): Try[Boolean] = Try {
      true
    }


}
