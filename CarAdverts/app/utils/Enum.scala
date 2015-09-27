package utils

/**
 * Template for Enums
 * 
 * @author steffen
 */
trait Enum[A] {
  trait Value { self: A => }
  val values: List[A]
  def parse(v: String) : Option[A] = values.find(_.toString() == v)
}