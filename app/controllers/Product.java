package controllers;


import models.StoresProducts;
import models.Stores;
import models.TypeProduct;
import models.User;
import models.Products;
import models.utils.AppException;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.data.Form;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import views.html.addStore;
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
		Form<Products> productForm = form(Products.class).bindFromRequest();
		 if (productForm.hasErrors()){
			 
			 return badRequest(addProduct.render(User.findByEmail(request().username()), productForm));
		 }
		
		 Products product= productForm.get();
		 product.save();
		return index();
	}
	
	public static Result getProducts(){
		List<Product> products = new Model.Finder(String.class, Products.class).all();
		return ok(toJson(products));
	}
	
	public static class addNewProduct {

        @Constraints.Required
        public String name;
        
        @Constraints.Required
        public TypeProduct typeProduct;

        /**
         * Validate the authentication.
         *
         * @return null if validation ok, string with details otherwise
         */

	
	public String validate() {
        if (isBlank(name)) {
            return "Name is required";
        }

        return null;
	}
    private boolean isBlank(String input) {
        return input == null || input.isEmpty() || input.trim().isEmpty();
    }
	
	}	
}
