package unitTests

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import models._
import org.joda.time.DateTime
import models.domainlogic._

@RunWith(classOf[JUnitRunner])
class CarAdvertSorterTester extends Specification {

  /*
   * Prepare list of CarAdverts
   */
  val list = List(
    new CarAdvert(4, "Car 1", Diesel,   30000, false, Some(10000),Some(new DateTime("2014-11-11"))),
    new CarAdvert(3, "Car 2", Gasoline, 40000, true, None, None),
    new CarAdvert(2, "Car 3", Diesel,   10000, false, Some(40000),Some(new DateTime("2010-11-11"))),
    new CarAdvert(1, "Car 4", Gasoline, 20000, true, None, None)
    
  )
   
  
  /*
   * Execute & validate tests:
   */
  "The list of car adverts " should {
    "be sorted according to their id" in {
      val sortedList = CarAdvertSorter.sortCarAdverts(list, "id")
      sortedList(0).id must beEqualTo(1)
      sortedList(1).id must beEqualTo(2)
      sortedList(2).id must beEqualTo(3)
      sortedList(3).id must beEqualTo(4)
    }
  "be sorted according to their title" in {
      val sortedList = CarAdvertSorter.sortCarAdverts(list, "title")
      sortedList(0).id must beEqualTo(4)
      sortedList(1).id must beEqualTo(3)
      sortedList(2).id must beEqualTo(2)
      sortedList(3).id must beEqualTo(1)
    }
  "be sorted according to their fuel" in {
      val sortedList = CarAdvertSorter.sortCarAdverts(list, "fuel")
      sortedList(0).id must beEqualTo(4)
      sortedList(1).id must beEqualTo(2)
      sortedList(2).id must beEqualTo(3)
      sortedList(3).id must beEqualTo(1)
    }  
  "be sorted according to their price" in {
      val sortedList = CarAdvertSorter.sortCarAdverts(list, "price")
      sortedList(0).id must beEqualTo(2)
      sortedList(1).id must beEqualTo(1)
      sortedList(2).id must beEqualTo(4)
      sortedList(3).id must beEqualTo(3)
    }  
  "be sorted according to their new status" in {
      val sortedList = CarAdvertSorter.sortCarAdverts(list, "isNew")
      sortedList(0).id must beEqualTo(4)
      sortedList(1).id must beEqualTo(2)
      sortedList(2).id must beEqualTo(3)
      sortedList(3).id must beEqualTo(1)
    }  
  "be sorted according to their mileage" in {
      val sortedList = CarAdvertSorter.sortCarAdverts(list, "mileage")
      sortedList(0).id must beEqualTo(3)
      sortedList(1).id must beEqualTo(1)
      sortedList(2).id must beEqualTo(4)
      sortedList(3).id must beEqualTo(2)
    }  
  "be sorted according to their registration date" in {
      val sortedList = CarAdvertSorter.sortCarAdverts(list, "mileage")
      sortedList(0).id must beEqualTo(3)
      sortedList(1).id must beEqualTo(1)
      sortedList(2).id must beEqualTo(4)
      sortedList(3).id must beEqualTo(2)
    }  
  
  }
  
}