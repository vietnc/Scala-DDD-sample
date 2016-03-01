package sep.com.bbs.domain.model.user

import sep.com.bbs.domain.shared._
import sep.com.bbs.domain.model.user.User

import scala.util.Try

object UserRepositoryImpl extends UserRepository{

    def findByEmail(email: String): Try[Option[User]] = {
      Try(None)
    }

    def resolveById(id: UserID): Try[Option[User]] = {
      Try(None)

    }
    def resolveAll(): Try[List[User]] = {
      Try(List())
    }

    def store(user: User): Try[Boolean] = {
      Try(true)
    }


}
