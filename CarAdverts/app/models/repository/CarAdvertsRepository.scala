package models.repository

import models.CarAdvert

/**
 * @author steffen
 */
trait CarAdvertsRepository {
  def findAll(): Seq[CarAdvert]
  def create(carAdvert: CarAdvert): CarAdvert
  def findById(id: Long): Seq[CarAdvert]
  def deleteById(id: Long)
  def update(carAdvert: CarAdvert): CarAdvert
}