package org.com.bbs.domain.model.user

import org.com.bbs.domain.shared.ValueObject
import org.com.bbs.infra.util.BbsSecure

case class PassWord( hashed: String) extends ValueObject

object PassWord{
  def fromRaw(raw: String):PassWord  = PassWord(BbsSecure.hash(raw))
}
