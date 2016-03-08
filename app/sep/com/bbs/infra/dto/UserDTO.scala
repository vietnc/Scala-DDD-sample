package sep.com.bbs.infra.dto

import play.api.libs.json.{Json, JsValue}
import scalikejdbc._


case class UserDTO(
                   id: String,
                   email: String,
                   password: String //hashed
                 )

/**
  * companion object to parse json
  */
object UserDTO {
  implicit val userDTOWrites = Json.writes[UserDTO]
  implicit val userDTOReads = Json.reads[UserDTO]
}

/**
  * db table
  */
object UserTable extends SQLSyntaxSupport[UserDTO] {
  override val tableName = "user"

  def apply(a: ResultName[UserDTO])(rs: WrappedResultSet): UserDTO = {
    UserDTO(
      rs.string(a.id),
      rs.string(a.email),
      rs.string(a.password)
    )
  }
}