package json

import play.api.libs.json.Writes
import models.Kudos
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.JsObject
import play.api.libs.json.Format
import play.api.libs.json.JsString
import play.api.libs.json.JsNumber

object JsonKudos {
  implicit object KudosFormat extends Writes[Kudos] {

        def writes(k: Kudos) = JsObject(Seq(
    		"id" -> JsNumber(k.id.get),
            "name" -> JsString(k.name),
            "targetId" -> JsNumber(k.targetId)
        ))
  }
}
