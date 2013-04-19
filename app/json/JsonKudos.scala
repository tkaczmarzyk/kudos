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
import play.api.libs.json.JsSuccess
import java.text.SimpleDateFormat
import java.sql.Date

object JsonKudos {
  val dateFormat = "yyyy-MM-dd"
  
  implicit object KudosFormat extends Writes[(Kudos, Person)] with Reads[Kudos] {

        def writes(tuple: (Kudos, Person)) = JsObject(
            tuple match {
              case (k: Kudos, p: Person) => Seq(
                "id" -> JsNumber(k.id.get),
                "name" -> JsString(k.name),
                "creationDate" -> JsString(new SimpleDateFormat(dateFormat).format(k.creationDate)),
                "text" -> JsString(k.text),
                "target" -> Json.obj("name" -> p.name, "photoUrl" -> p.photoUrl)
              )
            }
        )
        
        def reads(json: JsValue) = {
          val dateAsString = (json \ "creationDate").as[String]
          val creationDate = new Date(new SimpleDateFormat(dateFormat).parse(dateAsString).getTime())
          JsSuccess(Kudos(
        		(json \ "id").as[Option[Int]],
        		(json \ "name").as[String],
        		(json \ "targetId").as[Int],
        		(json \ "text").as[String],
        		creationDate
        ))
        } 
  }
}
