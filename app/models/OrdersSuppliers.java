package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.db.ebean.Model;

@Entity
public class OrdersSuppliers extends Model {
	
	@Id
	public Long id;

    public String reference;
    
    public String status ;
    
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date creationDate;
    
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date delivryDate;
    
	@ManyToOne
	public Suppliers supplier;

	 public static Model.Finder<Long, OrdersSuppliers> find = new Model.Finder<Long, OrdersSuppliers>(Long.class, OrdersSuppliers.class);
	

}
