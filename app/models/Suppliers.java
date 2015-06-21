package models;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;



import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.i18n.Messages;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import java.util.Date;


@Entity
public class Suppliers extends Model{
	@Id
	public Long id;

	@Constraints.Required
	@Formats.NonEmpty
	public String name;
	
	@Constraints.Required
	@Formats.NonEmpty
	public String adress1;
	
	public String adress2;
	
	@Constraints.Required
	@Formats.NonEmpty
	public int codePostal;
	
	@Constraints.Required
	@Formats.NonEmpty
	public String ville;
	
	@ManyToOne
	public User user;
	
	
	
	 public String validate(){
		 if ((name == null) || (adress1 == null) || (codePostal == 0) || (ville == null)) {
                return Messages.get("Il faut remplir tous les champs obligatoires");
            }
            return null;
        }
	

	 public static Model.Finder<Long, Suppliers> find = new Model.Finder<Long, Suppliers>(Long.class,Suppliers.class);

	
	    
	 public static HashMap<String, String> selectSuppliers()  {
	        HashMap<String, String> output = new HashMap<String, String>();

	        for(Suppliers s : Suppliers.find.all())   {

	        			output.put(s.id.toString(), s.name) ;
	        }
	        return output;
	    }




	    
	
}