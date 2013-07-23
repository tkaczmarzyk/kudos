package controllers;

import java.util.List;

import models.Person;
import play.data.Form;
import play.db.ebean.Model;
import static play.libs.Json.*;
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
		
		return redirect(routes.People.listAll());
	}
}
