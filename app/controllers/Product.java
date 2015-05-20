package controllers;


import models.StoresProducts;
import models.Stores;
import models.User;
import models.Products;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.data.Form;
import play.db.ebean.Model;

import views.html.product;
import views.html.addProduct;

import java.util.List;

import static play.libs.Json.toJson;
import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Product extends Controller {

	public static Result index() {
        return ok(product.render(User.findByEmail(request().username()),Products.find.orderBy("id asc").findList()));
    }
	
	final static Form<Products> productForm = form(Products.class);
    
    public static Result addProduct(){
    	return ok(addProduct.render(User.findByEmail(request().username()), productForm));
    }
	
	public static Result saveProduct(){
		Form<Products> form = productForm.bindFromRequest();
		Products product= form.get();
		product.save();
		return index();
	}
	
	public static Result getProducts(){
		List<Product> products = new Model.Finder(String.class, Products.class).all();
		return ok(toJson(products));
	}
	
}
