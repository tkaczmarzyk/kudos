package controllers

import scala.slick.driver.H2Driver.simple._
import scala.slick.driver.H2Driver.simple.Database.threadLocalSession
import play.api.mvc.Action
import play.api.mvc.Controller
import models.{Bar, Bars}
import global.Global
import play.api.libs.json.Json


object BarController extends Controller {

  def list = Action {
    val allBars = Global.database.withSession {
      val bars = for (b <- Bars) yield b
      bars.list
    }
    
    Ok(views.html.bars(allBars))
  }
  
  def newBar = Action { request =>
    val json = request.body.asJson.get // TODO pattern matching instead of just .get
    
    val barName = (json \ "name").as[String]
    
    Global.database withSession {
      Bars.insert(Bar(None, barName))
    }
    
    Redirect(routes.BarController.list)
  }
}