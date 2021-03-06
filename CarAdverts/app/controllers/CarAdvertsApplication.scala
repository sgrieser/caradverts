package controllers

import play.api._

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import models._
import models.domainlogic._
import models.repository.AnormCarAdvertsRepository

import org.joda.time.DateTime

class CarAdvertsApplication extends Controller {

  /**
   * Repository
   */
  val repository = new AnormCarAdvertsRepository
  
  /**
   * writes to convert car adverts into Json type
   */
   implicit val carWrites: Writes[CarAdvert] = (
    (JsPath \ "id").write[Long] and
    (JsPath \ "title").write[String]and
    (JsPath \ "fuel").write[FuelType] and
    (JsPath \ "price").write[Long] and
    (JsPath \ "isNew").write[Boolean] and
    (JsPath \ "mileage").writeNullable[Long] and
    (JsPath \ "firstRegistration").writeNullable[DateTime]
   )(unlift(CarAdvert.unapply))  
  
   
   /**
    * reads to convert Json types into car adverts
    */
     implicit val carReads: Reads[CarAdvert] = (
    (JsPath \ "id").read[Long] and
    (JsPath \ "title").read[String] and
    (JsPath \ "fuel").read[FuelType] and
    (JsPath \ "price").read[Long] and
    (JsPath \ "isNew").read[Boolean] and
    (JsPath \ "mileage").readNullable[Long] and
    (JsPath \ "firstRegistration").readNullable[DateTime]
   )(CarAdvert.apply _)
   
  
  /**
   * Lists all car adverts
   */
  def listAllCarAdverts(sortedBy : String) = Action {
    val json = Json.toJson( CarAdvertSorter.sortCarAdverts( repository.findAll(), sortedBy) )
    Ok(json)
  }
  
  /**
   * Saves a new car advert
   */
  def saveCarAdvert = Action(BodyParsers.parse.json) { request =>
    val carResult = request.body.validate[CarAdvert]
    carResult.fold(
        errors => {
          BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toFlatJson(errors)))
        },
        car => {
          
          try {
            CarAdvertValidator.validate(car)
            repository.create(car)
            Ok(Json.obj("status" -> "OK", "message" -> ("Car '" + car.title + "' saved")))
            
          }
          catch {
            case e : Exception => BadRequest(Json.obj("status" -> "KO", "message" -> e.getMessage))
          }                    
        }
    )
  }  
  
    /**
   * Updates an existing car advert
   */
  def updateCarAdvert = Action(BodyParsers.parse.json) { request =>
    val carResult = request.body.validate[CarAdvert]
    carResult.fold(
        errors => {
          BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toFlatJson(errors)))
        },
        car => {
          
          try {
            CarAdvertValidator.validate(car)
            repository.update(car)
            Ok(Json.obj("status" -> "OK", "message" -> ("Car '" + car.title + "' updated")))
          }
          catch {
            case e : Exception => BadRequest(Json.obj("status" -> "KO", "message" -> e.getMessage))
          }
        }
    )
  }  
  
  /**
   * Retrieves a specific car advert specified by its ID
   */
  def listCarAdvertById(id: Long) = Action {
    val json = Json.toJson(repository.findById(id))
    Ok(json)
  }
  
  /**
   * Deletes a specific car advert specified by its ID
   */
  def deleteCarAdvertById(id: Long) = Action {
    repository.deleteById(id)
    Ok("Car deleted")
  }

}
