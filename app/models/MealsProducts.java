package models;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

public class MealsProducts extends Model{
	
	@Id
	public Long id;
	
	 @ManyToOne
	 public Meals meal;
	 
	 @ManyToOne
	 public Products product; 
	 
	 public static Model.Finder<Long, MealsProducts> find = new Model.Finder<Long, MealsProducts>(Long.class, MealsProducts.class);
	

}
