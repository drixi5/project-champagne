package models;

import play.Logger;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import models.utils.AppException;
import models.utils.Hash;
import models.StoresProducts;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.data.format.Formats;
import play.i18n.Messages;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * User: yesnault
 * Date: 20/01/12
 */
@Entity
public class Products extends Model {
	
	@Id
	public Long id;

	@Constraints.Required
	@Formats.NonEmpty
	@Column(unique = true)
    public String name;
    
    @ManyToOne
    public TypeProduct typeProduct ;
    
	/*public ModelProduct(String name,TypeProduct typeProduct) {
        this.name = name;
        this.typeProduct = typeProduct;

    }
	
	public String getName(){
		 return name;
		}
		
		public void setName(String name){
			this.name=name;
		}
		
	public TypeProduct getTypeProdcut(){
		return typeProduct;
		}
			
		public void setTypeProduct(TypeProduct typeProduct){
			this.typeProduct = typeProduct;
		} */
    
    
    // -- Queries (long id, product.class)
    public static Model.Finder<Long, Products> find = new Model.Finder<Long, Products>(Long.class, Products.class);
    
    
//Select product for Stock (dont take one if it is already in the stock by id_store)
    public static HashMap<String, String> selectProducts(Long id)  {
        HashMap<String, String> output = new HashMap<String, String>();
        
        List<StoresProducts> stock = StoresProducts.find.all();
        List<Long> stockId = new ArrayList<>();
        for(StoresProducts sp : stock){
        	if(sp.store.id==id){
        	//Logger.debug(sp.store.id+"store");	
        	stockId.add(sp.product.id);
        //	Logger.debug(sp.product.id+"produit");
        	}
        }
        
        for(Products p : Products.find.all())   {
        		if(stockId.contains(p.id)){
        			
        		}
        		else{
        			output.put(p.id.toString(), p.name) ;
        		}
        		
        }
        return output;
    }
    
  //Select product for create meal (take just all product in stock by id_store)
    public static HashMap<String, String> selectProducts2(Long id)  {
        HashMap<String, String> output = new HashMap<String, String>();
        List<StoresProducts> stock = StoresProducts.find.all();
        List<Long> stockId = new ArrayList<>();
        for(StoresProducts sp : stock){
        	if(sp.store.id==id){
        	stockId.add(sp.product.id);
        	}
        }
        for(Products p : Products.find.all())   {
        	if(stockId.contains(p.id)){
        		output.put(p.id.toString(), p.name) ;
    		}			
        }
        return output;
    }
}
