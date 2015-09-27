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
    
    "send a fake list of car adverts" in new WithApplication{
      val caradverts = route(FakeRequest(GET, "/caradverts")).get

      status(caradverts) must equalTo(200)
      contentAsString(caradverts) must beEqualTo("""[{"id":1,"title":"Car 1"},{"id":2,"title":"Car 2"},{"id":3,"title":"Car 3"},{"id":4,"title":"Car 4"}]""")
    }
 
  }
}
