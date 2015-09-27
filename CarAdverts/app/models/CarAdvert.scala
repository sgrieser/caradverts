package models

import org.joda.time.DateTime

/**
 * Domain Model class to represent car adverts
 * 
 * @author steffen
 */
case class CarAdvert(id: Long, 
              title: String, 
              fuel : FuelType, 
              price: Long, 
              isNew : Boolean, 
              mileage: Option[Long], 
              firstRegistration : Option[DateTime] 
 )

              
     