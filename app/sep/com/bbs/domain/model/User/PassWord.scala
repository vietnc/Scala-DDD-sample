package sep.com.bbs.domain.model.user

import sep.com.bbs.domain.shared.ValueObject
import sep.com.bbs.infra.util.BbsSecure

case class PassWord( hashed: String) extends ValueObject

object PassWord{
  def fromRaw(raw: String):PassWord  = PassWord(BbsSecure.hash(raw))
}
