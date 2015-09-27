package models

import utils._

/**
 * @author steffen
 */
sealed trait FuelType extends FuelType.Value

case object Gasoline extends FuelType
case object Diesel extends FuelType

object FuelType extends Enum[FuelType] with SimpleEnumJson[FuelType] {
  val values = List(Gasoline, Diesel)
}