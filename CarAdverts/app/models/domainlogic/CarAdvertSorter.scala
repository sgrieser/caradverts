package models.domainlogic

import models.CarAdvert

/**
 * @author steffen
 */
object CarAdvertSorter {
  
  def sortCarAdverts(carAdverts : Seq[CarAdvert], sortedBy : String) : Seq[CarAdvert] = {
    
    sortedBy match {
      case "id" => carAdverts.sortBy(_.id)
      case "title" => carAdverts.sortBy(_.title)
      case "price" => carAdverts.sortBy(_.price)
      case "fuel" => carAdverts.sortBy(_.fuel.toString())
      case "mileage" => carAdverts.sortBy(_.mileage)
      case "isNew" => carAdverts.sortBy(_.isNew)
      case "firstRegistration" => carAdverts.sortBy(_.firstRegistration.toString())
      case _ => carAdverts.sortBy(_.id)
    } 
    
  }
  
}         