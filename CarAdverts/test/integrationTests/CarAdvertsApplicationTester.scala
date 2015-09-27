package integrationTests

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._
import play.api.mvc._
import play.api.http._
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import scala.math.BigDecimal.int2bigDecimal

/**
 * 
 */
@RunWith(classOf[JUnitRunner])
class CarAdvertsApplicationTester extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }
    
    "send an initial reply" in new WithApplication{
      val caradverts = route(FakeRequest(GET, "/")).get

      status(caradverts) must equalTo(200)
      contentAsString(caradverts) must beEqualTo("Your new application is ready.")
    }
 
  }
}
