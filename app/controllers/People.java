package controllers;

import static play.libs.Json.toJson;

import java.util.List;

import models.Person;

import org.codehaus.jackson.JsonNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class People extends Controller {

	
	public static Result listAll() {
		List<Person> people = Person.find.all();
		
		return ok(toJson(people));
	}
	
	public static Result add() {
		Person person = Json.fromJson(request().body().asJson(), Person.class);
		person.save();
		
		return ok();
	}

	public static Result update(Long id) {
		models.Person target = models.Person.find.byId(id);
		if (target != null) {
			JsonNode json = request().body().asJson();
			models.Person person = Json.fromJson(json, models.Person.class);
			target.smallPhotoUrl = person.smallPhotoUrl;
			target.bigPhotoUrl = person.bigPhotoUrl;
			target.name = person.name;
			target.save();
			return ok();
		} else {
			return notFound();
		}
	}
}
