package models.domainlogic


import models.CarAdvert

/**
 * @author steffen
 */
object CarAdvertValidator {
  
  def validate(carAdvert : CarAdvert) = {
    
    // in case of a new car, registrationDate and mileage shall be null
    if (carAdvert.isNew && (!carAdvert.firstRegistration.isEmpty || !carAdvert.mileage.isEmpty)) {
        throw new IllegalArgumentException("First registration date and mileage must not be set for new car adverts!");
    }
    if (!carAdvert.isNew && (carAdvert.firstRegistration.isEmpty || carAdvert.mileage.isEmpty)) {
        throw new IllegalArgumentException("First registration date and mileage must be set for new car adverts!");
    }
    
    
  }
  
}