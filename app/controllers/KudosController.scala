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
import play.api.data._
import play.api.data.Forms._
import views.html.helper.FieldConstructor._
import play.api.data.Form
import models.Kudos
import models.Kudos


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
  
//  def createKudos = Action { request =>
//  	val kudos = Json.fromJson(request.body.asJson.get).get
//  	Kudoses.insert(kudos)
//  }
  
  def submit = Action {request => {
      val form = request.body.asFormUrlEncoded.get
      
      val now = new java.sql.Date(System.currentTimeMillis())
      
	  val k = Kudos(id=None, name=form("forWhat")(0), text=form("description")(0), targetId=Integer.parseInt(form("targetId")(0)), creationDate=now)
	  
	  database withSession {
        Kudoses.insert(k)
      }
	  
	  Redirect(routes.Application.index)
    }
  }
  
  def showForm = Action {
    Ok(views.html.newKudos())
  }
}