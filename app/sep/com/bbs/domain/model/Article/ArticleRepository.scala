package sep.com.bbs.domain.model.article

import com.google.inject.ImplementedBy
import sep.com.bbs.domain.shared._

@ImplementedBy(classOf[ArticleRepositoryImpl])
trait ArticleRepository extends  iRepository[ArticleID, Article]