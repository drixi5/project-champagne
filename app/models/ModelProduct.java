package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import models.utils.AppException;
import models.utils.Hash;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.data.format.Formats;
import play.i18n.Messages;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import java.util.Date;
import java.util.HashMap;

/**
 * User: yesnault
 * Date: 20/01/12
 */
@Entity
public class ModelProduct extends Model {
	
	@Id
	public Long id;

	@Constraints.Required
	@Formats.NonEmpty
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
    public static Model.Finder<Long, ModelProduct> find = new Model.Finder<Long, ModelProduct>(Long.class, ModelProduct.class);
    
    

    public static HashMap<String, String> selectCollection()  {
        HashMap<String, String> output = new HashMap<String, String>();

        for(ModelProduct p : ModelProduct.find.all())  {
            output.put(p.id.toString(), p.name);
        }

        return output;
    }
}
