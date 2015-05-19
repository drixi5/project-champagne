package models;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.db.ebean.Model;
import java.util.Date;

@Entity
public class OrdersStores extends Model{
	
	@Id
	public Long id;

    public String reference;
    
    public String status ;
    
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date creationDate;
    
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date delivryDate;
    
	@ManyToOne
	public Stores store;

	 public static Model.Finder<Long, OrdersStores> find = new Model.Finder<Long, OrdersStores>(Long.class, OrdersStores.class);
	
}
