package sep.com.bbs.domain.article
import common.FakeAppHelper
import org.specs2.mock.Mockito
import play.api.test._
import org.specs2._
import sep.com.bbs.domain.model.article.{Article, ArticleRepositoryImpl}
import sep.com.bbs.domain.service.ArticleDomainService
import sep.com.bbs.domain.shared.ArticleID
import sep.com.bbs.infra.dto.ArticleDTO
import scala.util.{Try, Success, Failure}
import sep.com.bbs.infra.ArticleDAO

/**
  * Created by Viet on 3/4/2016.
  */
class ArticleRepositoryImplSpec extends PlaySpecification with FakeAppHelper with Mockito{

  val mockDAO = mock[ArticleDAO]
  val dtoArticleTest = ArticleDTO("1","title1","content1","mail1@gmail.com","2016-01-01 00:00:00")

  "ArticleRepository " should{
    "resolveAll(): " in {

      "no article, return empty List " in {

        (mockDAO.findAll()) returns (List(dtoArticleTest))
        val repo = new ArticleRepositoryImpl(mockDAO)
        repo.resolveAll().get.length must equalTo(1)
      }

    }
    "resolveById(id): " in {
      " return Some(Article) with valid id inputed" in{
        (mockDAO.findById("1")) returns (Some(dtoArticleTest))
        val repo = new ArticleRepositoryImpl(mockDAO)

        (repo.resolveById(ArticleID("1"))).get.get.title must equalTo("title1")

      }

    }

    "store(article): " in{
      "return false if save not successfull " in {
        (mockDAO.save(dtoArticleTest)) returns false
        val repo = new ArticleRepositoryImpl(mockDAO)

        (repo.store(ArticleDomainService.loadDTO(dtoArticleTest))) must equalTo(Success(false))
      }
    }


  }
}
