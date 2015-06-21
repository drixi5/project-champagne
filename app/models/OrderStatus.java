package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class OrderStatus extends Model{

	@Id
	public Long id;

    public String name;
    
 
    
    public static Model.Finder<Long, OrderStatus> find = new Model.Finder<Long, OrderStatus>(Long.class, OrderStatus.class);
    
}    