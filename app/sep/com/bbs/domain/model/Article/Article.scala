package sep.com.bbs.domain.model.Article

import sep.com.bbs.domain.shared._
import sep.com.bbs.infra.dto.ArticleDTO

/**
 * article domain
 */
case class Article (id: ArticleID,
                    title: String,
                    content: String,
                    author: Author) extends Entity

case class Author(email: String)