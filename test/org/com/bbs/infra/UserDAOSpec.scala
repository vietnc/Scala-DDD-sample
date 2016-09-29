package org.com.bbs.infra

import common.{DBSetting, mockDbSpec}
import org.specs2.specification.BeforeAll
import scalikejdbc.specs2.mutable.AutoRollback
import scalikejdbc.{DBSession, _}
import org.com.bbs.domain.shared.ID
import org.com.bbs.infra.dto.UserDTO
import org.com.bbs.infra.util.{BbsSecure}

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
      "save(dto) should save user to db" in new AutoRollbackWithFixture {
        val newDTO = UserDTO(ID.createUID,"vietngc2@gmail.com",BbsSecure.hash("123"))
        val ret = userDAO.save(newDTO)
        ret must equalTo(true)
        val userInDb = userDAO.findByEmail("vietngc2@gmail.com")
        userInDb.getOrElse(false) must not equalTo(false)
      }
    }
  }

}