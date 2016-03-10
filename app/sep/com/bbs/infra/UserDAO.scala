package sep.com.bbs.infra

import play.api.Logger
import scalikejdbc._
import sep.com.bbs.infra.dto.{ArticleTable, ArticleDTO, UserDTO, UserTable}

class UserDAO {

  private val u = UserTable.syntax("u")

  def findByEmail(email: String)(implicit session: DBSession = AutoSession): Option[UserDTO] = {
    withSQL {
      select
        .from(UserTable as u)
        .where.eq(u.email, email)
    }.map(UserTable(u.resultName)).single().apply()
  }

  def findById(id: String)(implicit session: DBSession = AutoSession): Option[UserDTO] = {
    None
  }

  def findAll()(implicit session: DBSession = AutoSession): List[UserDTO] = {
    List()
  }

  def save(dto: UserDTO)(implicit session: DBSession = AutoSession): Boolean ={
    val column = UserTable.column
    try{
      withSQL {
        insert
          .into(UserTable).namedValues(
          column.id -> dto.id,
          column.email -> dto.email,
          column.password -> dto.password
        )
      }.update.apply()
      true
    }catch {
      case e: Exception => Logger.error("[SQLException][UserDAO.save]: " + e.getMessage)
        false
    }
  }

}


