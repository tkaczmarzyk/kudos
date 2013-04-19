package models

import scala.slick.driver.H2Driver.simple._


case class Person(id: Option[Int] = None, name: String, photoUrl: String)

object People extends Table[Person]("people") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  
  def name = column[String]("name")
  
  def photoUrl = column[String]("photo_url")

  def * = id.? ~ name ~ photoUrl <>(Person, Person.unapply _)
}
