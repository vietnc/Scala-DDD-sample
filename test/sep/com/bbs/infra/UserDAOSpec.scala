package sep.com.bbs.infra

import common.{DBSetting, mockDbSpec}
import org.specs2.specification.BeforeAll
import scalikejdbc.specs2.mutable.AutoRollback
import scalikejdbc.{DBSession, _}
import sep.com.bbs.domain.shared.ID
import sep.com.bbs.infra.util.{BbsSecure}

class UserDAOSpec extends mockDbSpec with BeforeAll with DBSetting {
  implicit val session = AutoSession

  trait AutoRollbackWithFixture extends AutoRollback {
    override def fixture(implicit session: DBSession) {
      sql"""insert into user values (${ID.createUID}, ${"vietngc@gmail.com"}, ${BbsSecure.hash("123")},
         """.update.apply()
    }


    val userDAO = new UserDAO

    "UserDAO: " should {
      "findByEmail(email) should return result with correct email" in new AutoRollbackWithFixture {
        val userObj = userDAO.findByEmail("vietngc@gmail.com")
        userObj.get.email must equalTo("vietngc@gmail.com")
      }
      "findByEmail(email) should return None with incorrect email" in new AutoRollbackWithFixture {
        val userObj = userDAO.findByEmail("vietngcxxx@gmail.com")
        userObj.getOrElse("NotFound") must equalTo("NotFound")
      }
    }
  }

}