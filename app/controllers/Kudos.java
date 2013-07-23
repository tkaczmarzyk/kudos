package controllers;

import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import play.db.ebean.Model;
import play.libs.*;
import static play.libs.Json.*;


public class Kudos extends Controller {

	public static Result list() {
		int num = 12; // TODO get it from query string
		
		List<models.Kudos> recentKudos = models.Kudos.find.order("date desc").setMaxRows(num).findList();
			
		return ok(toJson(recentKudos));
	}
	
	public static Result add() {
		JsonNode json = request().body().asJson();
	
		models.Kudos kudos = Json.fromJson(json, models.Kudos.class);
		
		models.Person person = models.Person.find.byId(kudos.target.id);
		if (person != null) {
			kudos.target = person;
		}
		kudos.save();
		return ok();
	}
	
	public static Result find(Long id) {
		return ok(toJson(models.Kudos.find.byId(id)));
	}
	
	public static Result delete(Long id) {
		models.Kudos target = models.Kudos.find.byId(id);
		if (target != null) {
			target.delete();
			return ok();
		} else {
			return notFound();
		}
	}
}
