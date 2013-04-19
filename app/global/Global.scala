package global

import play.api.db.DB
import play.api.GlobalSettings
import scala.slick.driver.H2Driver.simple.Database.threadLocalSession
import scala.slick.driver.H2Driver.simple._
import play.api.Application
import play.api.Play.current
import models.Bars
import models.Kudoses
import models.Person
import models.People
import models.Kudos

 
object Global extends GlobalSettings {

  def database = Database.forDataSource(DB.getDataSource())
  
  override def onStart(app: Application) {
         
    database withSession {
      (Bars.ddl ++ Kudoses.ddl ++ People.ddl).create
      
      People.insertAll(Person(None, "Tomasz Kaczmarzyk"),
          Person(None, "Michał Jankowski"),
          Person(None, "Piotr Wyczółkowski"),
          Person(None, "Błażej Karmelita"))
      
      val participants = (for (p <- People) yield p).list

      var kudoses = 
        for (p <- participants)
          yield Kudos(None, "uczestnictwo w hackhatonie #1", p.id.get)

      val pw = participants.find(_.name.startsWith("Piotr")).get
      
      kudoses ::= Kudos(None, "za rozpykanie json mapperów ;)", pw.id.get) 
      
      Kudoses.insertAll(kudoses: _*)
    }
  }
}

