package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Suppliers extends Model{
	@Id
	public Long id;

	@Constraints.Required
	@Formats.NonEmpty
	public String name;
	
	@Constraints.Required
	@Formats.NonEmpty
	public String email;
	
	@Constraints.Required
	@Formats.NonEmpty
	public String adress1;
	
	public String adress2;
	
	@Constraints.Required
	@Formats.NonEmpty
	public int codePostal;
	
	@Constraints.Required
	@Formats.NonEmpty
	public String ville;
	
	@OneToOne
	public User user;
	
	
	public static  Model.Finder<Long, Suppliers> find = new  Model.Finder<Long, Suppliers>(Long.class, Suppliers.class);
}
