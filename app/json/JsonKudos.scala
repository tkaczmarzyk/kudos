package json

import play.api.libs.json.Writes
import models.Kudos
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.JsObject
import play.api.libs.json.Format
import play.api.libs.json.JsString
import play.api.libs.json.JsNumber
import models.Person
import play.api.libs.json.JsString
import play.api.libs.json.Reads
import play.api.libs.json.JsResult

object JsonKudos {
  implicit object KudosFormat extends Writes[(Kudos, Person)] with Reads[Kudos] {

        def writes(tuple: (Kudos, Person)) = JsObject(
            tuple match {
              case (k: Kudos, p: Person) => Seq(
                "id" -> JsNumber(k.id.get),
                "name" -> JsString(k.name),
                "text" -> JsString(k.text),
                "target" -> Json.obj("name" -> p.name, "photoUrl" -> p.photoUrl)
              )
            }
        )
        
        def reads(json: JsValue): Kudos = Kudos(
        		(json \ "id").as[Option[Int]],
        		(json \ "name").as[String],
        		(json \ "targetId").as[Int],
        		(json \ "text").as[String]
        )
  }
}
