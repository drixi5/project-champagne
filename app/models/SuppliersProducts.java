package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class SuppliersProducts extends Model{
	

	@Id
    public Long id;
	

	@ManyToOne 
    public Products product ;
	
	//@ManyToOne
   // public Suppliers supplier ;

	@ManyToOne 
    public Unit unit;

    @Constraints.Required
    @Formats.NonEmpty
    public int quantity;

    @Constraints.Required
    @Formats.NonEmpty
    public int threshold_max;
    
    @Constraints.Required
    @Formats.NonEmpty
    public int threshold_min;
    
    //@Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    //public Date dateCreation;
 
    
    public static Model.Finder<Long, SuppliersProducts> find = new Model.Finder<Long, SuppliersProducts>(Long.class,SuppliersProducts.class);
    

}
