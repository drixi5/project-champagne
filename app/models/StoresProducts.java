package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class StoresProducts extends Model {

	@Id
    public Long id;
	

	@ManyToOne 
    public Products product ;
	
	//@ManyToOne
   // public Stores store ;


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
    
    @Formats.NonEmpty
    public Boolean edit = false;
  

    
    public static Model.Finder<Long, StoresProducts> find = new Model.Finder<Long, StoresProducts>(Long.class,StoresProducts.class);
    
   

}
