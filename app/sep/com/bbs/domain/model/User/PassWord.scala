package sep.com.bbs.domain.model.user

import sep.com.bbs.domain.shared.ValueObject
import sep.com.bbs.infra.util.BbsSecure

case class PassWord(raw: String, hashed: String) extends ValueObject

object PassWord{
  def apply(hashStr:String):PassWord = PassWord("",hashStr)
  def fromRaw(raw: String):PassWord  = PassWord(raw,BbsSecure.hash(raw))
}
