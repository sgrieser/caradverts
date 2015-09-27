package acceptanceTests

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._

/**
 * Simple acceptance test. It just tries to connect to the WebService. The real functionality
 * is tested in the integration test.
 * 
 * -> regards as sufficient for this exercise; not applicable in real word application!
 */
@RunWith(classOf[JUnitRunner])
class CarAdvertsWebServiceTester extends Specification {

  // simple integration test to assure that the web service is accessible from a client
  // Note: all functional tests of the web service API are covered by the integration tests
  "WebService CarAdverts" should {

    "work from within a client" in new WithBrowser {

      browser.goTo("http://localhost:" + port + "/caradverts")

      val p = browser.pageSource
      
      browser.pageSource must contain("[]")
    }
  }
}
