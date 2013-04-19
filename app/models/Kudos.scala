package models

import scala.slick.driver.H2Driver.simple._


case class Kudos(id: Option[Int] = None, name: String, targetId: Int, text: String)

object Kudoses extends Table[Kudos]("kudos") {
  
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  
  def name = column[String]("name")

  def targetId = column[Int]("target_id")
  
  def target = foreignKey("target_fk", targetId, People)(_.id)
  
  def text = column[String]("text")
  
  
  def * = id.? ~ name ~ targetId ~ text <>(Kudos, Kudos.unapply _)
}
