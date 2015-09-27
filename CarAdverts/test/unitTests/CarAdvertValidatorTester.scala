package unitTests

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import models._
import org.joda.time.DateTime
import models.domainlogic._

@RunWith(classOf[JUnitRunner])
class CarAdvertValidatorTester extends Specification {

  "A car advert with a new car" should {
    "not contain a mileage" in {
      CarAdvertValidator.validate(new CarAdvert(1, "Car 4", Gasoline, 20000, true, Some(10000), None)) must throwA[IllegalArgumentException]
    }
    "not contain a registration date" in {
      CarAdvertValidator.validate(new CarAdvert(1, "Car 4", Gasoline, 20000, true, None, Some(new DateTime("2014-11-11")))) must throwA[IllegalArgumentException]
    }
    "neither contain a mileage nor a registration date" in {
      CarAdvertValidator.validate(new CarAdvert(1, "Car 4", Gasoline, 20000, true, None, None)) must not(throwA[IllegalArgumentException])
    }
  }
  
    "A car advert with an old car" should {
     "not miss a registration date" in {
      CarAdvertValidator.validate(new CarAdvert(1, "Car 4", Gasoline, 20000, false, Some(10000), None)) must throwA[IllegalArgumentException]
    }
    "not miss a mileage" in {
      CarAdvertValidator.validate(new CarAdvert(1, "Car 4", Gasoline, 20000, false, None, Some(new DateTime("2014-11-11")))) must throwA[IllegalArgumentException]
    }
    "neither miss a mileage nor a registration date" in {
      CarAdvertValidator.validate(new CarAdvert(1, "Car 4", Gasoline, 20000, false, Some(10000), Some(new DateTime("2014-11-11")))) must not(throwA[IllegalArgumentException])
    }
      
    }
  
}