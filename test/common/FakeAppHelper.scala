package common

import play.api.test.FakeApplication

trait FakeAppHelper {
  implicit def fakeApp = FakeApplication(
    additionalConfiguration = Map(
      "play.crypto.secret" -> "testbbs"
    )
  )

}