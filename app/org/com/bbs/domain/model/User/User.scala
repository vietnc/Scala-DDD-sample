package org.com.bbs.domain.model.user

import org.com.bbs.domain.shared._

case class User(id: UserID,email: String, password: PassWord) extends Entity
