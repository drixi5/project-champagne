package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TypeUser extends Model{
	
	@Id
	public Long id;

    public String name;
    
 
    
    public static Model.Finder<Long, TypeUser> find = new Model.Finder<Long,TypeUser>(Long.class, TypeUser.class);

}
