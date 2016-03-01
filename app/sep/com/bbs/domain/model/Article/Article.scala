package sep.com.bbs.domain.model.article

import org.joda.time.DateTime
import sep.com.bbs.domain.shared._

/**
 * article model
 */
case class Article (id: ArticleID,
                    title: String,
                    content: String,
                    author: Author,
                    createdDate:DateTime ) extends Entity

case class Author(email: String)