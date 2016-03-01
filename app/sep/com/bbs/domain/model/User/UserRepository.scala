package sep.com.bbs.domain.model.User

import sep.com.bbs.domain.model.user.User
import sep.com.bbs.domain.shared.{iRepository, UserID}

trait UserRepository extends  iRepository[UserID, User] {
  def findByEmail(email: String): Option[User]
}
