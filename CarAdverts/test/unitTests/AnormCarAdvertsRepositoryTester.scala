package unitTests

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import models._
import models.repository.AnormCarAdvertsRepository
import play.api.db._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class AnormCarAdvertsRepositoryTester extends Specification{
 
    "The db " should {
     "create a new CarAdvert and store it in the DB" in new WithApplication{
       val repo = new AnormCarAdvertsRepository()
       repo.create(new CarAdvert(1, "Car 4"))
       val result = repo.findAll()
       result must have size(1)
       result(0).id must beEqualTo(1)
       result(0).title must beEqualTo("Car 4")
     }
   }

}



