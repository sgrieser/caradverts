package acceptanceTests

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
@RunWith(classOf[JUnitRunner])
class CarAdvertsWebServiceTester extends Specification {

  // simple integration test to assure that the web service is accessible from a client
  // Note: all functional tests of the web service API are covered by the integration tests
  "WebService CarAdverts" should {

    "work from within a client" in new WithBrowser {

      browser.goTo("http://localhost:" + port + "/caradverts")

      val p = browser.pageSource
      
      browser.pageSource must contain("""[{"id":1,"title":"Car 1"},{"id":2,"title":"Car 2"},{"id":3,"title":"Car 3"},{"id":4,"title":"Car 4"}]""")
    }
  }
}
