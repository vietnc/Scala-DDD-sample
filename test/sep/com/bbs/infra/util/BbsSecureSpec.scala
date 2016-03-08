package sep.com.bbs.infra.util

import play.api.test.PlaySpecification

class BbsSecureSpec extends PlaySpecification{
  "BbsSecureSpec" should {
    "Password sample hashed in md5" in {
      BbsSecure.hash("123") must equalTo("202cb962ac59075b964b07152d234b70")
    }
  }
}
