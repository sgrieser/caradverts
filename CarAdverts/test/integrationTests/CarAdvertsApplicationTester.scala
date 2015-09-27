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
    
    "return an empty list of car adverts" in new WithApplication{
      val caradverts = route(FakeRequest(GET, "/caradverts")).get

      status(caradverts) must equalTo(200)
      contentType(caradverts) must beSome.which(_ == "application/json")
      contentAsString(caradverts) must beEqualTo("[]")
    }
    
    "save a new car advert"  in new WithApplication{
      
      val jsonBody = Json.obj("id" -> JsNumber(1),
                              "title" -> JsString("BMW X3"))
  
      val caradverts = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
                              
      status(caradverts.get) must equalTo(200)
      contentType(caradverts.get) must beSome.which(_ == "application/json")
      contentAsString(caradverts.get) must contain ("Car 'BMW X3' saved")

      
    }
  
   "reject a new car advert with an id which already exists"  in new WithApplication{

     // send first car advert
      val jsonBody = Json.obj("id" -> JsNumber(1),
                              "title" -> JsString("BMW X3"))
  
      val caradverts = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
                              
      status(caradverts.get) must equalTo(200)
      
      
      // send second car advert
      val jsonBody2 = Json.obj("id" -> JsNumber(1),
                              "title" -> JsString("VW Passat"))
  
      val caradverts2 = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody2).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
      
      
      status(caradverts2.get) must equalTo(400)
      contentType(caradverts2.get) must beSome.which(_ == "application/json")
      val s = contentAsString(caradverts2.get)
      contentAsString(caradverts2.get) must contain ("Eindeutiger Index oder Primärschlüssel verletzt")

      
    }  
   
   "return list of all car adverts" in new WithApplication{
 
    // send first car advert
      val jsonBody = Json.obj("id" -> JsNumber(1),
                              "title" -> JsString("BMW X3"))
  
      val caradverts = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
                              
      
      // send second car advert
      val jsonBody2 = Json.obj("id" -> JsNumber(2),
                              "title" -> JsString("VW Passat"))
  
      val caradverts2 = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody2).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
     
     
     // get list of all car adverts:
     val caradverts3 = route(FakeRequest(GET, "/caradverts")).get

      status(caradverts3) must equalTo(200)
      contentType(caradverts3) must beSome.which(_ == "application/json")
      contentAsString(caradverts3) must contain("BMW X3")
      contentAsString(caradverts3) must contain("VW Passat")
    }
    
 
    "return list of one car advert specified by its ID" in new WithApplication{
 
    // send first car advert
      val jsonBody = Json.obj("id" -> JsNumber(1),
                              "title" -> JsString("BMW X3"))
  
      val caradverts = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
                              
      
      // send second car advert
      val jsonBody2 = Json.obj("id" -> JsNumber(2),
                              "title" -> JsString("VW Passat"))
  
      val caradverts2 = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody2).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
     
     
     // get car advert by ID:
     val caradverts3 = route(FakeRequest(GET, "/caradverts/1")).get

      status(caradverts3) must equalTo(200)
      contentType(caradverts3) must beSome.which(_ == "application/json")
      contentAsString(caradverts3) must contain("BMW X3")
      contentAsString(caradverts3) must not(contain("VW Passat"))
    }
    
    "return an empty message in case the ID of a car advert does not exists" in new WithApplication {
    // send car advert
      val jsonBody = Json.obj("id" -> JsNumber(1),
                              "title" -> JsString("BMW X3"))
  
      val caradverts = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
                              
     
     // get car advert by ID:
     val caradverts3 = route(FakeRequest(GET, "/caradverts/2")).get

      status(caradverts3) must equalTo(200)
      contentType(caradverts3) must beSome.which(_ == "application/json")
      contentAsString(caradverts3) must beEqualTo("[]")
    }
    
    "delete a car advert" in new WithApplication{
 
    // send first car advert
      val jsonBody = Json.obj("id" -> JsNumber(1),
                              "title" -> JsString("BMW X3"))
  
      val caradverts = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
                              
      
      // send second car advert
      val jsonBody2 = Json.obj("id" -> JsNumber(2),
                              "title" -> JsString("VW Passat"))
  
      val caradverts2 = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody2).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
     
     // delete first car advert
     val caradverts3 = route(FakeRequest(DELETE, "/caradverts/1")).get     
     status(caradverts3) must equalTo(200)
                           
                              
     // get list of all car adverts:
     val caradverts4 = route(FakeRequest(GET, "/caradverts")).get

      status(caradverts4) must equalTo(200)
      contentType(caradverts4) must beSome.which(_ == "application/json")
      contentAsString(caradverts4) must not(contain("BMW X3"))
      contentAsString(caradverts4) must contain("VW Passat")
    }   
    
    "update a car advert"  in new WithApplication{
      
      val jsonBody = Json.obj("id" -> JsNumber(1),
                              "title" -> JsString("BMW X3"))
  
      val caradverts = route(FakeRequest(POST, "/caradverts").
                              withJsonBody(jsonBody).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
                              
      // send an update
      val jsonBody2 = Json.obj("id" -> JsNumber(1),
                              "title" -> JsString("BMW X6"))
  
      val caradverts2 = route(FakeRequest(PUT, "/caradverts").
                              withJsonBody(jsonBody2).
                              withHeaders(HeaderNames.CONTENT_TYPE -> "application/json"))
                              
      status(caradverts2.get) must equalTo(200)
      
     // get list of all car adverts:
     val caradverts3 = route(FakeRequest(GET, "/caradverts")).get

      status(caradverts3) must equalTo(200)
      contentType(caradverts3) must beSome.which(_ == "application/json")
      contentAsString(caradverts3) must contain("BMW X6")
    }
    
   
  }
}
