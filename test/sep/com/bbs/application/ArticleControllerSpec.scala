package sep.com.bbs.application

import common.FakeAppHelper
import controllers.HtmlController
import org.specs2.mock.Mockito
import play.api.inject.guice.GuiceInjectorBuilder
import play.api.test.{FakeRequest, WithApplication, ResultExtractors, PlaySpecification}
import play.libs.Json

class ArticleControllerSpec extends PlaySpecification with FakeAppHelper with ResultExtractors with Mockito {
  running(fakeApp) {
    val guice = new GuiceInjectorBuilder().build()
    val controller = guice.instanceOf[ArticleController]

    "ArticleController: " should {
      "list() -should return more than 1 articles" in new WithApplication(fakeApp) {

        val request = FakeRequest(GET, "/article")
        val result = call(controller.list, request)
        status(result) must beEqualTo(OK)
        contentType(result) must beSome("application/json")
        (contentAsJson(result) \\ "id").length > 1
      }
      "getById(correctId) -with correct id should  return article content, email of post creator" in new WithApplication(fakeApp) {

        val request = FakeRequest(GET, "/article/d170b908-9bae-4465-a426-f9c5270caba5")
        val result = call(controller.getById("d170b908-9bae-4465-a426-f9c5270caba5"),request)
        status(result) must beEqualTo(OK)
        contentType(result) must beSome("application/json")
        (contentAsJson(result) \\ "content").length must equalTo(1)
        (contentAsJson(result) \\ "email").length must equalTo(1)
      }
      "getById(wrongId) -with wrong id, should  return not found exception" in new WithApplication(fakeApp) {

        val request = FakeRequest(GET, "/article/xxx")
        val result = call(controller.getById("xxx"), request)
        status(result) must beEqualTo(NOT_FOUND)
        contentAsString(result) must contain("error")
      }
      "saveArticle - should save posted article to db, then we can see it in the list" in new WithApplication(fakeApp) {

        val postTitle = "new Article add " + scala.util.Random.nextInt(1000)
        val request = FakeRequest(POST, "/article/add").withFormUrlEncodedBody(("title",postTitle),
          ("content", "new Content"), ("email","vietngc@gmail.com"))
        val result = call(controller.saveArticle, request)
        status(result) must beEqualTo(OK)
        val listRequest = FakeRequest(GET, "/article")
        val listResult = controller.list().apply(listRequest)
        status(listResult) must beEqualTo(OK)
        (contentAsJson(listResult) \\ "title").find(p => p.as[String] == postTitle)
      }

    }
  }
}
