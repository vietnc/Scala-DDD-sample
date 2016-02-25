package sep.com.bbs.domain.model.Article

import java.util.Date
import sep.com.bbs.domain.shared._

/**
 * article domain
 */
case class Article (id: ArticleID,
                    title: String,
                    content: String,
                    author: Author,
                    createdDate:Date ) extends Entity

case class Author(email: String)