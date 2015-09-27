package models.repository

import models.CarAdvert

/**
 * @author steffen
 */
trait CarAdvertsRepository {
  def findAll(): Seq[CarAdvert]
  def create(carAdvert: CarAdvert): CarAdvert
}