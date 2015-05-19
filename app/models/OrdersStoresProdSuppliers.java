package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class OrdersStoresProdSuppliers extends Model{
	
	@Id
	public Long id;
	
	public int quantity;
	
	@ManyToOne
	public Products product;
	
	@ManyToOne
	public Stores store;
	
	@ManyToOne
	public Suppliers supplier;
	
	public static Model.Finder<Long, OrdersStoresProdSuppliers> find = new Model.Finder<Long, OrdersStoresProdSuppliers>(Long.class, OrdersStoresProdSuppliers.class);

}
