package models;

import java.util.HashMap;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TypeUser extends Model{
	
	@Id
	public Long id;

    public String name;
    
 
    
    public static Model.Finder<Long, TypeUser> find = new Model.Finder<Long,TypeUser>(Long.class, TypeUser.class);
    
    public static HashMap<String, String> selectTypeUser()  {
        HashMap<String, String> output = new HashMap<String, String>();

        for(TypeUser p : TypeUser.find.all())   {

        			output.put(p.id.toString(), p.name) ;
        }
        return output;
    }

}
