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
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import views.html.addStore;
import views.html.editStore;
import views.html.editProduct;
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
    	Products product = new Products();
    	return ok(addProduct.render(User.findByEmail(request().username()), productForm, product, routes.Product.saveProduct()));
    }
	
	public static Result saveProduct(){
		Form<Products> productForm = form(Products.class).bindFromRequest();
		 if (productForm.hasErrors()){
			 Products product = new Products();
			 return badRequest(addProduct.render(User.findByEmail(request().username()), productForm, product, routes.Product.saveProduct()));
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
        @Formats.NonEmpty
        public String name;
	
        //@Constraints.Required
        //public TypeProduct typeProduct;

        /**
         * Validate the authentication.
         *
         * @return null if validation ok, string with details otherwise
         */

	
	public String validate() {
        if (isBlank(name)) {
            return "Name is required";
        }
        //if (isBlank(typeProduct)) {
            //return "Type is required";
        //}

        return null;
	}
	
    private boolean isBlank(String input) {
        return input == null || input.isEmpty() || input.trim().isEmpty();
       
    }
	}
    //private boolean isBlank(TypeProduct input) {
        //return input == null || input.isEmpty() || input.trim().isEmpty();
        
    //}
    
	 public static Result edit(Long id)  {
	        Products product = Products.find.byId(id);
	        
	        return ok(editProduct.render(User.findByEmail(request().username()),productForm.fill(product), product));
	    }
	 
	 public static Result update(Long id)  {
		 	Form<Products> filledForm = productForm.bindFromRequest();

	        /*if(filledForm.hasErrors())  {
	            //can't pull out of the form if there are errors
	            Book book = Book.find.byId(id);
	            return ok(edit.render(filledForm, book));
	        } else {*/
	            Products product = filledForm.get();
	            product.update(id);
	            return index();
	        
	    }

	public static Result Drinks(){
		List<Product> products = new Model.Finder(String.class, Products.class).all();
		return ok(toJson(products));
	}
	
}
