package controllers

import scala.slick.driver.H2Driver.simple._
import scala.slick.driver.H2Driver.simple.Database.threadLocalSession
import play.api.mvc.Action
import play.api.mvc.Controller
import models.{Kudos, Kudoses}
import global.Global._
import play.api.libs.json.Json
import json.JsonKudos.KudosFormat


object KudosController extends Controller {

  def list = Action {
    val kudos = database.withSession {
      (for (b <- Kudoses) yield b).list
    }
    
    Ok(Json.toJson(kudos.map(Json.obj("kudoses", _))))
  }
}