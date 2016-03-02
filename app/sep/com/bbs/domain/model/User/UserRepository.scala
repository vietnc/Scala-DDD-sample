package sep.com.bbs.domain.model.user

import com.google.inject.ImplementedBy
import sep.com.bbs.domain.model.article.ArticleRepositoryImpl
import sep.com.bbs.domain.shared._

@ImplementedBy(classOf[ArticleRepositoryImpl])
trait UserRepository extends  iRepository[UserID, User]