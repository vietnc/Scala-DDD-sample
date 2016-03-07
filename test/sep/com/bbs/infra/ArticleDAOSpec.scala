package sep.com.bbs.infra

import java.util.UUID

import common.{DBSetting, mockDbSpec}
import org.specs2.specification.BeforeAll
import scalikejdbc.specs2.mutable.AutoRollback
import scalikejdbc.{DBSession, _}
import sep.com.bbs.domain.shared.ID
import sep.com.bbs.infra.dto.ArticleDTO
import sep.com.bbs.infra.util.DateTime

class ArticleDAOSpec extends mockDbSpec with BeforeAll with DBSetting{
  implicit val session = AutoSession

  trait AutoRollbackWithFixture extends AutoRollback {
    override def fixture(implicit session: DBSession) {
      sql"""insert into article values (${ID.createUID}, ${"article title 1"}, ${"Article content 01"},
         ${"mail01@gmail.com"}, STR_TO_DATE('03-04-2016 00:00:00','%m-%d-%Y %H:%i:%s'))""".update.apply()

      sql"""insert into article values (${ID.createUID}, ${"article title 2"}, ${"Article content 02"},
        ${"mail02@gmail.com"}, STR_TO_DATE('03-04-2016 00:00:00','%m-%d-%Y %H:%i:%s'))""".update.apply()
    }
  }

  val articleDAO = new ArticleDAO

  "ArticleDao: " should {
    "findAll() should return 2 articles" in new AutoRollbackWithFixture {
      val listArticle = articleDAO.findAll()
      listArticle.length must equalTo(2)
    }
    "findById(id) should return result with correctID" in new AutoRollbackWithFixture {
      val listArticle = articleDAO.findAll()
      articleDAO.findById(listArticle(0).id).get.title must contain("article title")
    }
    "findById(id) should return None with incorrectID" in new AutoRollbackWithFixture {
      articleDAO.findById("incorrectId").getOrElse("None") must equalTo("None")
    }
    "save(dto) should save to db" in new AutoRollbackWithFixture {
      val newDTO = ArticleDTO(ID.createUID,"title_test_save","content_test_save","vietngc@gmail.com",DateTime.toString(DateTime.getDate()))
      val ret = articleDAO.save(newDTO)
      ret must equalTo(true)
      val listArticle = articleDAO.findAll()
      listArticle.length must equalTo(3)
    }
  }
}