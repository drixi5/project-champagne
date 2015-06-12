package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class MealsProducts extends Model{
	
	@Id
	public Long id;
	
	 @ManyToOne
	 public Meals meal;
	 
	 @ManyToOne
	 public Products product; 
	 
	 @Constraints.Required
	 @Formats.NonEmpty
	 public int quantity;
	 
	 public static Model.Finder<Long, MealsProducts> find = new Model.Finder<Long, MealsProducts>(Long.class, MealsProducts.class);
	

}
