package models

import scala.slick.driver.H2Driver.simple._


case class Bar(id: Option[Int] = None, name: String)

object Bars extends Table[Bar]("bar") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  
  def name = column[String]("name")

  def * = id.? ~ name <>(Bar, Bar.unapply _)
}
