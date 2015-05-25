package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.i18n.Messages;

@Entity
public class Stores extends Model {
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
	
	//@ManyToOne
	//public User user;
	public Stores()  {
    }
	
	public Stores(String name,String email, String adress1, String adress2, int codePostal, String ville) {
        this.name = name;
        this.email = email;
        this.adress1 = adress1;
        this.adress2 = adress2;
        this.codePostal=codePostal;
        this.ville=ville;
    }
	
	/*public String getName(){
	 return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getEmail(){
		 return email;
	}
		
	public void setEmail(String email){
		 this.email = email;
	}
	
	public String getAdress1(){
		 return adress1;
	}
		
	public void setAdress1(String adress1){
		this.adress1 = adress1;
	}

	public int getCodePostal(){
		 return codePostal;
	}
		
	public void setCodePostal(int codePostal){
		this.codePostal = codePostal;
	}
	
	public String getVille(){
		 return ville;
	}
		
	public void setVille(String ville){
		this.ville = ville;
	}
	*/
	
	 public String validate(){
		 if ((name == null) || (email == null) || (adress1 == null) || (codePostal == 0) || (ville == null)) {
                return Messages.get("Il faut remplir tous les champs obligatoires");
            }
            return null;
        }
	
	
	 
	/*public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    if (name == null) {
        errors.add(new ValidationError("name", "The name is required."));
    }
    if (adress1 == null) {
        errors.add(new ValidationError("name", "The name is required."));
    }
    if (codePostal == 0) {
        errors.add(new ValidationError("name", "The name is required."));
    }
    if (ville == null) {
        errors.add(new ValidationError("name", "The name is required."));
    }
    return errors.isEmpty() ? null : errors;
}*/
	
	
	public static  Model.Finder<Long, Stores> find = new  Model.Finder<Long, Stores>(Long.class, Stores.class);

}
