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


object KudosController extends Controller {
  
  def list = Action {
    val kudos: List[(Kudos, Person)] = database.withSession {
      (for (k <- Kudoses; p <- k.target) yield (k, p)).list
    }
    
    Ok(Json.toJson(kudos))
  }
  
  def findById(id: Int) = Action {
    val found = database.withSession {
      (for (k <- Kudoses if k.id === id) yield k).firstOption
    }
    
    found match {
      case Some(kudos) => Ok(kudos.toString)
      case None => NotFound("no such element")
    }
  }
}