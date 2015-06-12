package models;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Meals extends Model{
	
	@Id
	public Long id;

	@Constraints.Required
	@Formats.NonEmpty
    public String name;
	
	@ManyToOne
    public Stores store ;
	
	@ManyToOne
    public TypeMeal typeMeal ;
 
 
    public static Model.Finder<Long, Meals> find = new Model.Finder<Long, Meals>(Long.class, Meals.class);
    
    
    public static HashMap<String, String> selectMeal()  {
        HashMap<String, String> output = new HashMap<String, String>();

        for(Unit u : Unit.find.all())  {
            output.put(u.id.toString(), u.name);
        }

        return output;
    }

}
