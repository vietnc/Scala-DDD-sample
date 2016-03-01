package sep.com.bbs.domain.model.article

import java.util.Date
import sep.com.bbs.domain.shared._

/**
 * article model
 */
case class Article (id: ArticleID,
                    title: String,
                    content: String,
                    author: Author,
                    createdDate: Date ) extends Entity

case class Author(email: String)