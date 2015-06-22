package models;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.Form;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.i18n.Messages;
import play.mvc.Result;

@Entity
public class Suppliers extends Model{
	@Id
	public Long id;

	@Constraints.Required
	@Formats.NonEmpty
	public String name;
	
	@Constraints.Required
	@Formats.NonEmpty
	public String email;
	
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
	
	public Suppliers()  {
    }
	
	public Suppliers(String name,String email, String adress1, String adress2, int codePostal, String ville) {
        this.name = name;
        this.email = email;
        this.adress1 = adress1;
        this.adress2 = adress2;
        this.codePostal=codePostal;
        this.ville=ville;
    }
	
	
	 public String validate(){
		 if ((name == null) || (email == null) || (adress1 == null) || (codePostal == 0) || (ville == null)) {
               return Messages.get("Il faut remplir tous les champs obligatoires");
           }
           return null;
       }

	
	
		public static  Model.Finder<Long, Suppliers> find = new  Model.Finder<Long, Suppliers>(Long.class, Suppliers.class);
		
		public static HashMap<String, String> selectSuppliers()  {
	        HashMap<String, String> output = new HashMap<String, String>();

	        for(Suppliers p : Suppliers.find.all())   {

	        			output.put(p.id.toString(), p.name) ;
	        }
	        return output;
	    }}
