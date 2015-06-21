package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Orders extends Model{
	
	@Id
	public Long id;
	
	@Constraints.Required
	@Formats.NonEmpty
	public int quantity;
	
	public int status ;
	    
	@Formats.DateTime(pattern = "dd-MM-yyyy")
	public Date creationDate;
	    
	@Formats.DateTime(pattern = "dd-MM-yyyy")
	public Date delivryDate;
	
	@ManyToOne 
	public StoresProducts storesProducts;
	
	@ManyToOne
	public Stores store;
	
	@ManyToOne 
	public SuppliersProducts suppliersProducts;
	
	@ManyToOne
	public Suppliers supplier;
	
	public static Model.Finder<Long, Orders> find = new Model.Finder<Long, Orders>(Long.class, Orders.class);

}
