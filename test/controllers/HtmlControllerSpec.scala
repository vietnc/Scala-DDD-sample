package controllers

import common.FakeAppHelper
import org.specs2.mock.Mockito
import play.api.inject._
import play.api.inject.guice.GuiceInjectorBuilder
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, PlaySpecification, ResultExtractors, WithApplication}

class HtmlControllerSpec extends PlaySpecification with FakeAppHelper with ResultExtractors with Mockito {
  running(fakeApp) {
    val guice = new GuiceInjectorBuilder()
      .build()
    val controller = guice.instanceOf[HtmlController]

    "HtmlController" should {
      "get article list" in new WithApplication(fakeApp) {

        val request = FakeRequest(GET, "/")
        val result = controller.showArticleListScreen().apply(request)
        status(result) must beEqualTo(200)
        contentAsString(result) must contain ("Article list")
      }
    }
  }
}