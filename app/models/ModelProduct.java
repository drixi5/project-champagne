package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import models.utils.AppException;
import models.utils.Hash;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Column;

import javax.persistence.ManyToOne;

import java.util.Date;

/**
 * User: yesnault
 * Date: 20/01/12
 */
@Entity
public class ModelProduct extends Model {
	
	@Id
	public Long id;

    public String name;
    
    @ManyToOne
    public TypeProduct type ;
    
    @ManyToOne
    public Unit unit ;
    
    // -- Queries (long id, product.class)
    public static Model.Finder<Long, ModelProduct> find = new Model.Finder<Long, ModelProduct>(Long.class, ModelProduct.class);
}