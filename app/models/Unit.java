package models;

import java.util.HashMap;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Unit extends Model{

	@Id
	public Long id;

    public String name;
    
 
    
    public static Model.Finder<Long, Unit> find = new Model.Finder<Long, Unit>(Long.class, Unit.class);
    
    
    public static HashMap<String, String> selectCollection()  {
        HashMap<String, String> output = new HashMap<String, String>();

        for(Unit u : Unit.find.all())  {
            output.put(u.id.toString(), u.name);
        }

        return output;
    }
}
