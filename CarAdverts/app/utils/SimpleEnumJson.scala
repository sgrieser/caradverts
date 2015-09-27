package utils

import play.api.libs.json._

/**
 * Allows automatic conversion between Enum and Jason Type
 * 
 * @author steffen
 */
trait SimpleEnumJson[A] {
  self: Enum[A] =>

  implicit def reads: Reads[A] = new Reads[A] {
    def reads(json: JsValue): JsResult[A] = json match {
      case JsString(v) => parse(v) match {
        case Some(a) => JsSuccess(a)
        case _ => JsError(s"String value ($v) is not a valid enum item")
      }
      case _ => JsError("String value expected")
    }
  }

  implicit def writes[A]: Writes[A] = new Writes[A] {
    def writes(v: A): JsValue = JsString(v.toString)
  }
}