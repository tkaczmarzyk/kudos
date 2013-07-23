package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import play.db.ebean.Model;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Kudos extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	
	public String reason;
	
	public String details;
	
	public Date date = new Date();
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	public List<Person> targets = new ArrayList<Person>();
	
	
	public static Finder<Long, Kudos> find = new Finder<Long, Kudos>(Long.class, Kudos.class);
}