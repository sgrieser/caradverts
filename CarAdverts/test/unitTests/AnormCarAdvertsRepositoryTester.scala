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
       repo.create(new CarAdvert(1, "Car 1"))
       val result = repo.findAll()
       result must have size(1)
       result(0).id must beEqualTo(1)
       result(0).title must beEqualTo("Car 1")
     }
     
     "return the list of all car adverts" in new WithApplication {
       val repo = new AnormCarAdvertsRepository()
       repo.create(new CarAdvert(1, "Car 1"))
       repo.create(new CarAdvert(2, "Car 2"))
       repo.create(new CarAdvert(3, "Car 3"))
 
       val result = repo.findAll()
       result must have size(3)
       result(0).id must beEqualTo(1)
       result(0).title must beEqualTo("Car 1")
       result(1).id must beEqualTo(2)
       result(1).title must beEqualTo("Car 2")
       result(2).id must beEqualTo(3)
       result(2).title must beEqualTo("Car 3")
     }
     
     "return a car advert specified by its ID" in new WithApplication {
       
       val repo = new AnormCarAdvertsRepository()
       repo.create(new CarAdvert(1, "Car 1"))
       repo.create(new CarAdvert(2, "Car 2"))
       repo.create(new CarAdvert(3, "Car 3"))
       
       val result = repo.findById(2)
       result must have size(1)
       result(0).id must beEqualTo(2)
       result(0).title must beEqualTo("Car 2")      
       
     }  
     
     "return an empty list in case the ID of a car advert does not exists" in new WithApplication {
       val repo = new AnormCarAdvertsRepository()
       repo.create(new CarAdvert(1, "Car 1"))
       repo.create(new CarAdvert(2, "Car 2"))
       repo.create(new CarAdvert(3, "Car 3"))
       
       val result = repo.findById(4)
       result must have size(0)       
     }      
     
     "delete a car advert" in new WithApplication {
       val repo = new AnormCarAdvertsRepository()
       repo.create(new CarAdvert(1, "Car 1"))
       repo.create(new CarAdvert(2, "Car 2"))
       repo.create(new CarAdvert(3, "Car 3"))
 
       repo.deleteById(2)
       
       val result = repo.findAll()
       result must have size(2)
       result(0).id must beEqualTo(1)
       result(0).title must beEqualTo("Car 1")
       result(1).id must beEqualTo(3)
       result(1).title must beEqualTo("Car 3")
     }
     
     "reject to delete a car advert if ID does not exist" in new WithApplication {
       val repo = new AnormCarAdvertsRepository()
       repo.create(new CarAdvert(1, "Car 1"))
       repo.create(new CarAdvert(2, "Car 2"))
       repo.create(new CarAdvert(3, "Car 3"))
 
       repo.deleteById(4)
       
       val result = repo.findAll()
       result must have size(3)      
     } 
    
     "update a car advert" in new WithApplication {
       val repo = new AnormCarAdvertsRepository()
       repo.create(new CarAdvert(1, "Car 1"))
       
       repo.update(new CarAdvert(1, "Car X"))
 
       val result = repo.findAll()
       result must have size(1)
       result(0).id must beEqualTo(1)
       result(0).title must beEqualTo("Car X")
       
     }
     
    "reject to update a car advert if ID does not exist" in new WithApplication {
        val repo = new AnormCarAdvertsRepository()
       repo.create(new CarAdvert(1, "Car 1"))
       
       repo.update(new CarAdvert(2, "Car X"))
 
       val result = repo.findAll()
       result must have size(1)
       result(0).id must beEqualTo(1)
       result(0).title must beEqualTo("Car 1")      
     } 
    
    
     
   }

}



