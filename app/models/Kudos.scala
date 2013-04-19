package models

import scala.slick.driver.H2Driver.simple._


case class Kudos(id: Option[Int] = None, name: String, targetId: Int)

object Kudoses extends Table[Kudos]("kudos") {
  
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  
  def name = column[String]("name")

  def targetId = column[Int]("target_id")
  
  def target = foreignKey("target_fk", targetId, People)(_.id)
  
  
  def * = id.? ~ name ~ targetId <>(Kudos, Kudos.unapply _)
  
}
