import scala.slick.driver.H2Driver.simple._
import scala.slick.driver.H2Driver.simple.Database.threadLocalSession
import org.scalatest.FlatSpec
import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test.FakeApplication
import models.Bars
import models.Bar
import global.Global

class BarSpec extends Specification {

  Class.forName("org.h2.Driver")

  "A Bar" should {

    "be creatable" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        Global.database.withSession {
          Bars.insert(Bar(None, "foo"))
          val b = for (b <- Bars) yield b
          b.first.id.get should be equalTo(1)
        }
      }
    }
    
  }
}
