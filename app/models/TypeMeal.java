package models;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class TypeMeal extends Model {
	
	@Id
	public Long id;

    public String name;
    
    
    public static Model.Finder<Long, TypeMeal> find = new Model.Finder<Long,TypeMeal>(Long.class, TypeMeal.class);
    
    public static HashMap<String, String> selectTypeMeal()  {
        HashMap<String, String> output = new HashMap<String, String>();

        for(TypeMeal p : TypeMeal.find.all())   {

        			output.put(p.id.toString(), p.name) ;
        }
        return output;
    }

}
