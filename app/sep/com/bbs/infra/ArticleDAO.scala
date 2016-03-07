package sep.com.bbs.infra

import javax.inject._

import play.api.Logger
import scalikejdbc._
import sep.com.bbs.infra.dto.{ArticleDTO, ArticleTable}

@Singleton
case class ArticleDAO @Inject()(){

  // ad-hoc session provider on the REPL
  implicit val session = AutoSession

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

  def save(dto: ArticleDTO)(implicit session: DBSession = AutoSession): Boolean ={
    val column = ArticleTable.column
    try{
    withSQL {
      insert
        .into(ArticleTable).namedValues(
        column.id -> dto.id,
        column.title -> dto.title,
        column.content -> dto.content,
        column.email -> dto.email,
        column.createdDate -> dto.createdDate
      )
    }.update.apply()
    true
    }catch {
      case e: Exception => Logger.error("[SQLException][ArticleDAO.save]: " + e.getMessage)
        false
    }
  }

}


