package sep.com.bbs.infra

import sep.com.bbs.infra.dto.ArticleDTO

/**
 * Created by Viet on 2/24/2016.
 */
object ArticleDAO {
  def findById(id: String): ArticleDTO={
    //query DB
    ArticleDTO("uniqueID", "du lich Van Long", "Cung dep day", "VietNC")
  }
  def findAll(): List[ArticleDTO]= {
    List(ArticleDTO("uniqueID", "du lich Van Long", "Cung dep day", "VietNC"),
      ArticleDTO("uniqueID2", "du lich Van Long2", "Cung dep day2", "VietNC"))
  }
  def save(articleDTO: ArticleDTO) ={
    //save
  }
}


