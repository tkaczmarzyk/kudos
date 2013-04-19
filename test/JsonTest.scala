import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import json.JsonKudos.KudosFormat
import models.Kudos
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.junit.internal.runners.JUnit4ClassRunner
import java.sql.Date
import java.text.SimpleDateFormat

@RunWith(classOf[JUnitRunner])
class JsonTest extends FunSuite with ShouldMatchers {

  test("Kudos fromJson") {
    val kudos = Json.parse("""{"id":1,"name":"uczestnictwo w hackhatonie #1","targetId":1,"text":"some text","creationDate":"2013-04-19"}""").as[Kudos]
    val expectedDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2013-04-19").getTime())
    kudos should be === Kudos(Some(1), "uczestnictwo w hackhatonie #1", 1, "some text", expectedDate)
  }
}