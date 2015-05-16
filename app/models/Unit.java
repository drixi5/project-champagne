package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Unit extends Model{

	@Id
	public Long id;

    public String name;
    
    
    public static Model.Finder<Long, Unit> find = new Model.Finder<>(Long.class, Unit.class);
}
