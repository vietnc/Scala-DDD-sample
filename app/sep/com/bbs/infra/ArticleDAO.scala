package sep.com.bbs.infra

import scalikejdbc._
import sep.com.bbs.infra.dto.{ArticleTable, ArticleDTO}

/**
 * Created by Viet on 2/24/2016.
 */
object ArticleDAO {
//  def findById(id: String): ArticleDTO={
//    //query DB
//    ArticleDTO("uniqueID", "du lich Van Long", "Cung dep day", "VietNC","2015/12/06 0:0:1")
//  }
//  def findAll(): List[ArticleDTO]= {
//    List(ArticleDTO("uniqueID", "du lich Van Long", "Cung dep day", "VietNC", "2015/12/06 0:0:1"),
//      ArticleDTO("uniqueID2", "du lich Van Long2", "Cung dep day2", "VietNC", "2015/12/06 2:0:2"))
//  }
//  def save(articleDTO: ArticleDTO) ={
//    //save
//  }

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

    def save(articleDTO: ArticleDTO) ={
      //save
    }
}


