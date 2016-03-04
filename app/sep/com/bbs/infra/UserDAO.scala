package sep.com.bbs.infra

import play.api.Logger
import scalikejdbc._
import sep.com.bbs.infra.dto.{ArticleTable, ArticleDTO, UserDTO, UserTable}

object UserDAO {

  private val u = UserTable.syntax("u")

  def findByEmail(email: String)(implicit session: DBSession = AutoSession): Option[UserDTO] = {
    withSQL {
      select
        .from(UserTable as u)
        .where.eq(u.email, email)
    }.map(UserTable(u.resultName)).single().apply()
  }

  def findById(id: String)(implicit session: DBSession = AutoSession): Option[UserDTO] = {
    withSQL {
      select
        .from(UserTable as u)
        .where.eq(u.id, id)
    }.map(UserTable(u.resultName)).single().apply()
  }

  def findAll()(implicit session: DBSession = AutoSession): List[UserDTO] = {
    withSQL {
      select
        .from(UserTable as u)
    }.map(UserTable(u.resultName)).list().apply()
  }

  def save(dto: ArticleDTO)(implicit session: DBSession = AutoSession): Boolean ={
    try{
    withSQL {
      insert
        .into(ArticleTable).namedValues(
        u.column("id") -> dto.id,
        u.column("email") -> dto.email
      )
    }.update.apply()
    true
    }catch {
      case e: Exception => Logger.error("[SQLException][ArticleDAO.save]: " + e.getMessage)
        false
    }
  }

}


