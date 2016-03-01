package common

import org.specs2.mock.Mockito
import org.specs2.specification.{BeforeAll, AfterAll, BeforeEach}
import play.api.db.{Database, Databases}
import play.api.db.evolutions.Evolutions
import play.api.test._

class mockDbSpec extends PlaySpecification with BeforeAll with AfterAll with Mockito  with TestDBHelper {
  sequential

  def beforeAll() = Evolutions.applyEvolutions(testDb)

  def afterAll() = {
    Evolutions.cleanupEvolutions(testDb)
    testDb.shutdown()
  }
}