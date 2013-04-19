package controllers

import scala.slick.driver.H2Driver.simple._
import scala.slick.driver.H2Driver.simple.Database.threadLocalSession
import play.api.mvc.Action
import play.api.mvc.Controller
import models.{Kudos, Kudoses}
import global.Global._
import play.api.libs.json.Json
import json.JsonKudos.KudosFormat
import views.html.defaultpages.badRequest
import models.Person
import models.People
import play.api.libs.json.JsObject


object PeopleController extends Controller {
  
  def list = Action {
    val people = database.withSession {
      (for (p <- People) yield p).list
    }
    
    val json = people.map(p =>
      Json.obj(
          "id" -> p.id,
          "name" -> p.name
      )
    )
    
    Ok(json.mkString("[", ",", "]"))
  }
}