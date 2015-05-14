package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stores extends Model {
	@Id
    public Long id;
	
	public String name;
	
	public String adresse1;
	
	public String adresse2;
	
	public int codePostal;
	
	public String villes;
	

}
