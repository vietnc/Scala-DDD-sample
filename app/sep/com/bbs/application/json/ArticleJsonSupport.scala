package sep.com.bbs.application.json

import play.api.libs.json._
import sep.com.bbs.infra.dto.ArticleDTO


object ArticleJsonSupport {
  implicit val articleWrites: Writes[ArticleDTO] = Writes[ArticleDTO] (
    (article: ArticleDTO) => {
      JsObject(Seq(
        "id" -> JsString(article.id),
        "title" -> JsString(article.title),
        "content" -> JsString(article.content)
      ))
    }
  )

  def write(article: ArticleDTO): JsValue = Json.toJson(article)
  def write(articles: Seq[ArticleDTO]): JsValue = Json.toJson(articles)
}
