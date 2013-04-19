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
import java.sql.Date

 
object Global extends GlobalSettings {

  def database = Database.forDataSource(DB.getDataSource())
  
  override def onStart(app: Application) {
         
    database withSession {
      (Bars.ddl ++ Kudoses.ddl ++ People.ddl).create
      
      People.insertAll(Person(None, "Tomasz Kaczmarzyk", "http://cohesiva.com/images/people_thumbs/tomekk_alt.jpg"),
          Person(None, "Michał Jankowski", "http://cohesiva.com/images/people_thumbs/michal_alt.jpg"),
          Person(None, "Piotr Wyczółkowski", "http://cohesiva.com/images/people_thumbs/piotrekw_alt.jpg"),
          Person(None, "Błażej Karmelita", "http://cohesiva.com/images/people_thumbs/blazej_alt.jpg"))
      
      val participants = (for (p <- People) yield p).list

      var kudoses = 
        for (p <- participants)
          yield Kudos(None, "uczestnictwo w hackhatonie #1", p.id.get, "za wybranie klawiatury zamiast piwa w piątek wieczorem!", new Date(System.currentTimeMillis()))

      val pw = participants.find(_.name.startsWith("Piotr")).get
      
      kudoses ::= Kudos(None, "za rozpykanie json mapperów ;)", pw.id.get, "po prostu usiadł i wykonał zadanie -- a kłód pod nogami było wiele :)", new Date(System.currentTimeMillis())) 
      
      Kudoses.insertAll(kudoses: _*)
    }
  }
}

