package sep.com.bbs.domain.model.user

import com.google.inject.ImplementedBy
import sep.com.bbs.domain.model.article.ArticleRepositoryImpl
import sep.com.bbs.domain.shared._

import scala.util.Try

@ImplementedBy(classOf[UserRepositoryImpl])
trait UserRepository extends  iRepository[UserID, User]{
  def findByEmail(email: String): Try[Option[User]]
}