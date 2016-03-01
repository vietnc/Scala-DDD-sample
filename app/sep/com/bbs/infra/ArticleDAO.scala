package sep.com.bbs.infra

import play.api.Logger
import scalikejdbc._
import sep.com.bbs.infra.dto.{ArticleTable, ArticleDTO}

/**
 * Created by Viet on 2/24/2016.
 */
object ArticleDAO {

  private val a = ArticleTable.syntax("a")

  def findById(id: String)(implicit session: DBSession = AutoSession): Option[ArticleDTO] = {
    withSQL {
      select
        .from(ArticleTable as a)
        .where.eq(a.id, id)
    }.map(ArticleTable(a.resultName)).single().apply()
  }

  def findAll()(implicit session: DBSession = AutoSession): List[ArticleDTO] = {
    withSQL {
      select
        .from(ArticleTable as a)
    }.map(ArticleTable(a.resultName)).list().apply()
  }

  def save(dto: ArticleDTO): Boolean ={
    try{
    withSQL {
      insert
        .into(ArticleTable).namedValues(
        a.column("id") -> dto.id,
        a.column("title") -> dto.title,
        a.column("content") -> dto.content,
        a.column("email") -> dto.email,
        a.column("created_date") -> dto.createdDate
      )
    }.update.apply()
    true
    }catch {
      case e: Exception => Logger.error("[SQLException][ArticleDAO.save]: " + e.getMessage)
        false
    }
  }

}


