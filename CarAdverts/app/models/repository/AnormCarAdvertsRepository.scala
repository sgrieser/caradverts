package models.repository

import play.api.db._
import anorm._
import anorm.SqlParser._
import play.api.Play.current
import org.joda.time.DateTime
import models._

/**
 * @author steffen
 */
class AnormCarAdvertsRepository extends CarAdvertsRepository{
  
  /**
   * Create table for CarAdverts
   */
  DB.withConnection { implicit connection =>
      SQL("""
          create table if not exists CARS (
            id INTEGER NOT NULL, 
            title VARCHAR(255),
            fuel VARCHAR(255), 
            price INTEGER,
            isNew BOOLEAN,
            mileage INTEGER,
            firstRegistration VARCHAR(255),
            PRIMARY KEY (id)
          );
            
      """).execute()
    }
 
  
 val simple = {
    get[Long]("id") ~
    get[String]("title") ~
    get[String]("fuel") ~
    get[Long]("price") ~
    get[Boolean]("isNew") ~
    get[Option[Long]]("mileage") ~
    get[Option[String]]("firstRegistration") map {
      case id~title~fuel~price~isNew~mileage~firstRegistration 
        => CarAdvert(id, title, FuelType.parse(fuel).get, price, isNew, mileage, firstRegistration match {
          case Some(dateAsString) => Option(new DateTime(dateAsString))
          case None => None
        } )
    }
  }

  /**
   * Retrievs all car adverts from DB
   */
  def findAll(): Seq[CarAdvert] = {
    DB.withConnection { implicit connection =>
      SQL("select * from CARS").as(simple *)
    }
    
  }
 
  /**
   * Retrievs a car advert specified by its ID
   */
  def findById(id: Long): Seq[CarAdvert] = {
    DB.withConnection { implicit connection =>
      SQL("select * from CARS where id = " + id).as(simple *)
    }
  }
 
  /**
   * Stores a new car advert into DB
   */
  def create(car: CarAdvert): CarAdvert = {
    
  DB.withConnection { implicit connection =>
      SQL("""
          insert into CARS(id, title,fuel,price,isNew,mileage,firstRegistration) 
          VALUES (
            {id},
            {title},
            {fuel},
            {price},
            {isNew},
            {mileage},
            {firstRegistration}
          )  
      """).
        on('id -> car.id,  
           'title -> car.title,
           'fuel -> car.fuel.toString(),
           'price -> car.price,
           'isNew -> car.isNew,
           'mileage -> car.mileage,
           'firstRegistration -> dateOptionToStringOption(car.firstRegistration)           
           ).executeInsert()
      
        
      car
    }    
    
  }  
  
  /**
   * Deletes a car advert specified by it ID
   */
  def deleteById(id: Long) = {
    DB.withConnection { implicit connection =>
      SQL("delete from CARS where id={id}").
        on('id -> id).executeUpdate()
    }
  }
  
  /**
   * Updates an existing car advert
   */
  def update(carAdvert: CarAdvert): CarAdvert = {
    DB.withConnection { implicit connection =>
      SQL("""
        UPDATE CARS 
        SET title={title},
            fuel={fuel}, 
            price={price},
            isNew={isNew},
            mileage={mileage},
            firstRegistration={firstRegistration}
        WHERE id={id};
        """).
        on('id -> carAdvert.id,  
           'title -> carAdvert.title,
           'fuel -> carAdvert.fuel.toString,
           'price -> carAdvert.price,
           'isNew -> carAdvert.isNew,
           'mileage -> carAdvert.mileage,
           'firstRegistration -> dateOptionToStringOption(carAdvert.firstRegistration)
           ).executeUpdate()
    }
    
    carAdvert
  } 
  
    def dateOptionToStringOption(dateTimeOption : Option[DateTime]) : Option[String] = {
    dateTimeOption match {
       case Some(x) => Option(dateTimeOption.get.toString)
       case _ => None
    }
  }
  
 
}