package sep.com.bbs.infra.dto

import org.joda.time.DateTime
import play.api.libs.json.{Json, JsValue}
import scalikejdbc._


case class ArticleDTO(
   id: String,
   title: String,
   content: String,
   email: String,
   createdDate: DateTime
 )
/**
  * companion object to parse json
  */
object ArticleDTO {
  implicit val articleDTOWrites = Json.writes[ArticleDTO]
  implicit val articleDTOReads = Json.reads[ArticleDTO]
}

/**
  * db table
  */
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