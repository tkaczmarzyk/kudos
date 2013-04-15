package global

import play.api.db.DB
import play.api.GlobalSettings
import scala.slick.driver.H2Driver.simple.Database.threadLocalSession
import scala.slick.driver.H2Driver.simple._
import play.api.Application
import play.api.Play.current
import models.Bars

 
object Global extends GlobalSettings {

  def database = Database.forDataSource(DB.getDataSource())
  
  override def onStart(app: Application) {
         
    database withSession {
      Bars.ddl.create
    }
  }
}

