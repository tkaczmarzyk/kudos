package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.db.ebean.Model;


@Entity
public class Person extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	
	@ManyToMany(mappedBy="targets")
	@JsonIgnore
	public List<Kudos> kudos = new ArrayList<Kudos>();
	
	public String name;
	
	public String smallPhotoUrl;
	
	public String bigPhotoUrl;
	
	
	public static Finder<Long, Person> find = new Finder<Long, Person>(Long.class, Person.class);
}
