package sep.com.bbs.domain.shared

import java.util.UUID

abstract class ID extends ValueObject{
  def equals(other: Any): Boolean

  def createUID(): String = {
    UUID.randomUUID().toString
  }
}

final case class ArticleID(val guid: String) extends  ID{

  override def equals(x: Any): Boolean = {
    x match{
      case ArticleID(id) => this.guid == id
      case _ => false
    }
  }
}

final case class UserID(val guid: String) extends  ID{

  override def equals(x: Any): Boolean = {
    x match{
      case UserID(id) => this.guid == id
      case _ => false
    }
  }
}