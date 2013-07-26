package controllers;

import static play.libs.Json.toJson;

import java.util.List;

import org.codehaus.jackson.JsonNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;


public class Kudos extends Controller {

	public static Result list(java.lang.Integer max) {
		List<models.Kudos> recentKudos = models.Kudos.find.order("date desc").setMaxRows(max).findList();
			
		return ok(toJson(recentKudos));
	}
	
	public static Result add() {
		JsonNode json = request().body().asJson();
	
		models.Kudos kudos = Json.fromJson(json, models.Kudos.class);
		
		updatePersonBasedOnId(kudos);
		kudos.save();
		return ok();
	}

	private static void updatePersonBasedOnId(models.Kudos kudos) {
		models.Person person = models.Person.find.byId(kudos.target.id);
		if (person != null) {
			kudos.target = person;
		} else {
			throw new IllegalArgumentException("Could not find person with id = "+kudos.target.id);
		}
	}
	
	public static Result find(Long id) {
		return ok(toJson(models.Kudos.find.byId(id)));
	}

	public static Result update(Long id) {
		models.Kudos target = models.Kudos.find.byId(id);
		if (target != null) {
			JsonNode json = request().body().asJson();
			models.Kudos kudos = Json.fromJson(json, models.Kudos.class);
			updatePersonBasedOnId(kudos);
			target.reason = kudos.reason;
			target.details = kudos.details;
			target.date = new java.util.Date();
			target.save();
			return ok();
		} else {
			return notFound();
		}
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
