package models

import scala.slick.driver.H2Driver.simple._
import java.sql.Date


case class Kudos(id: Option[Int] = None, name: String, targetId: Int, text: String, creationDate: Date)

object Kudoses extends Table[Kudos]("kudos") {
  
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  
  def name = column[String]("name")

  def targetId = column[Int]("target_id")
  
  def target = foreignKey("target_fk", targetId, People)(_.id)
  
  def text = column[String]("text")
  
  def creationDate = column[Date]("creation_date")
  
  
  def * = id.? ~ name ~ targetId ~ text ~ creationDate <>(Kudos, Kudos.unapply _)
}
