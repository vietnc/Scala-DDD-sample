package org.com.bbs.domain.model.article

import com.google.inject.ImplementedBy
import org.com.bbs.domain.shared._

@ImplementedBy(classOf[ArticleRepositoryImpl])
trait ArticleRepository extends  iRepository[ArticleID, Article]