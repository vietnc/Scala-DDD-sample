package sep.com.bbs.domain.model.User

import sep.com.bbs.domain.model.user.User
import sep.com.bbs.domain.shared.UserID

object UserRepositoryImpl extends UserRepository{

    def findByEmail(email: String): Option[User] = {
      None
    }

    def resolveById(id: UserID): Option[User] = {
      None

    }
    def resolveAll(): List[User] = {
      List()
    }

    def store(user: User): Boolean = {
      true
    }


}
