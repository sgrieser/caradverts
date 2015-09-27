package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import models._

class CarAdvertsApplication extends Controller {

  /**
   * writes to convert car adverts into Json type
   */
   implicit val carWrites: Writes[CarAdvert] = (
    (JsPath \ "id").write[Long] and
    (JsPath \ "title").write[String]
   )(unlift(CarAdvert.unapply))  
  
  
  /**
   * Lists all car adverts
   */
  def listAllCarAdverts = Action {
    
    val fakeListOfCarAdverts = List(
        new CarAdvert(1, "Car 1"),
        new CarAdvert(2, "Car 2"),
        new CarAdvert(3, "Car 3"),
        new CarAdvert(4, "Car 4")   
    )
     
    val json = Json.toJson( fakeListOfCarAdverts )
    Ok(json)
  }

}
