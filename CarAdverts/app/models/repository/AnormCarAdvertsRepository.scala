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
            title varchar(255),
            PRIMARY KEY (id)
          );
            
      """).execute()
    }
 
  
  val simple = {
    get[Long]("id") ~
    get[String]("title") map {
      case id~title 
        => CarAdvert(id, title)
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
            insert into CARS(id, title) 
            VALUES (
              {id},
              {title}
            )  
        """).
          on('id -> car.id,  
             'title -> car.title          
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
        SET title={title}
        WHERE id={id};
        """).
        on('id -> carAdvert.id,  
           'title -> carAdvert.title
           ).executeUpdate()
    }
    
    carAdvert
  }    
  
 
}