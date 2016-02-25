package sep.com.bbs.domain.shared

import java.util.UUID

abstract class ID extends ValueObject{
  def equals(other: Any): Boolean

  def createUID(): String = {
    UUID.randomUUID().toString
  }
}

final case class ArticleID(val value: String) extends  ID{

  override def equals(x: Any): Boolean = {
    x match{
      case ArticleID(id) => this.value == id
      case _ => false
    }
  }
}

final case class UserID(val value: String) extends  ID{

  override def equals(x: Any): Boolean = {
    x match{
      case UserID(id) => this.value == id
      case _ => false
    }
  }
}