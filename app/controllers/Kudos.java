package controllers;

import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;

import models.Person;

import play.mvc.Controller;
import play.mvc.Result;
import play.db.ebean.Model;
import play.libs.*;
import static play.libs.Json.*;


public class Kudos extends Controller {

	public static Result list() {
		int num = 10; // TODO get it from query string
		
		List<models.Kudos> recentKudos = models.Kudos.find.order("date desc").setMaxRows(num).findList();
			
		return ok(toJson(recentKudos));
	}
	
	public static Result add() {
		JsonNode json = request().body().asJson();
	
		models.Kudos kudos = Json.fromJson(json, models.Kudos.class);
		
		Iterator<JsonNode> targetIds = json.path("targetIds").getElements();
		while (targetIds.hasNext()) {
			Person target = Person.find.byId(targetIds.next().asLong());
			if (target != null) {
				kudos.targets.add(target);
			}
		}
		
		kudos.save();
		
		return redirect(routes.Kudos.list());
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
