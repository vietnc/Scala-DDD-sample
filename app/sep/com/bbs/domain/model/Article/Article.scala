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

/**
 * companion object for support functions
 */
object Article{
  def load(dto : ArticleDTO): Article ={
    Article(ArticleID(dto.id),
      dto.title,
      dto.content,
      Author(dto.author))
  }

  def getDTO(ar: Article): ArticleDTO={
    ArticleDTO(ar.id.guid,
      ar.title, ar.content,
      ar.author.email)
  }
}
