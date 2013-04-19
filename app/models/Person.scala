package models

import scala.slick.driver.H2Driver.simple._


case class Person(id: Option[Int] = None, name: String)

object People extends Table[Person]("people") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  
  def name = column[String]("name")

  def * = id.? ~ name <>(Person, Person.unapply _)
}
