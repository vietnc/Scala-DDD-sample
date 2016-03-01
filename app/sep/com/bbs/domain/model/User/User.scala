package sep.com.bbs.domain.model.user

import sep.com.bbs.domain.shared._

case class User(id: UserID, email: String) extends Entity
