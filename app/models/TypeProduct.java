package models;

import java.util.HashMap;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TypeProduct extends Model {
	
	@Id
	public Long id;

    public String name;
    
    public static Model.Finder<Long, TypeProduct> find = new Model.Finder<Long, TypeProduct>(Long.class, TypeProduct.class);

    public static HashMap<String, String> selectCollection()  {
        HashMap<String, String> output = new HashMap<String, String>();

        for(TypeProduct t : TypeProduct.find.all())  {
            output.put(t.id.toString(), t.name);
        }

        return output;
    }
    
}
