package sep.com.bbs.infra.dto
import sep.com.bbs.infra.util.DateTime
import play.api.libs.json.{Json, JsValue}
import scalikejdbc._


case class UserDTO(
                   id: String,
                   email: String
                 )

/**
  * companion object to parse json
  */
object UserDTO {
  implicit val articleDTOWrites = Json.writes[ArticleDTO]
  implicit val articleDTOReads = Json.reads[ArticleDTO]
}

/**
  * db table
  */
object UserTable extends SQLSyntaxSupport[UserDTO] {
  override val tableName = "user"

  def apply(a: ResultName[UserDTO])(rs: WrappedResultSet): UserDTO = {
    UserDTO(
      rs.string(a.id),
      rs.string(a.email)
    )
  }
}