package sep.com.bbs.infra.dto

import org.joda.time.DateTime
import scalikejdbc._

/**
 * Created by Viet on 2/24/2016.
 */
case class ArticleDTO(
   id: String,
   title: String,
   content: String,
   email: String,
   createdDate: DateTime
 )

object ArticleTable extends SQLSyntaxSupport[ArticleDTO] {
  override val tableName = "article"

  def apply(a: ResultName[ArticleDTO])(rs: WrappedResultSet): ArticleDTO = {
    ArticleDTO(
      rs.string(a.id),
      rs.string(a.title),
      rs.string(a.content),
      rs.string(a.email),
      rs.jodaDateTime(a.createdDate)
    )
  }
}