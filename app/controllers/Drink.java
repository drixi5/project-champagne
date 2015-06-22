package controllers;

import static play.data.Form.form;
import static play.libs.Json.toJson;

import java.util.List;


import controllers.Dashboard.Quantity;
import models.Products;
import models.StoresProducts;
import models.User;
import play.Logger;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.drink;

@Security.Authenticated(Secured.class)
public class Drink extends Controller{
	
	public static Result Drinks(){
		Logger.debug("drink main page");
    	return ok(drink.render(User.findByEmail(request().username()), StoresProducts.find.where("product.typeProduct.id = 4").findList()));
	}
	
	 public static Result Increment(Long id){
	    	StoresProducts drink = StoresProducts.find.byId(id);
	    	drink.quantity +=1;
	    	drink.save();
	    	return Drinks();
	 }
	 
	 
	 public static Result Decrement(Long id){
			StoresProducts drink = StoresProducts.find.byId(id);
			drink.quantity -=1;
			drink.save();
	    	return Drinks();
	    }
}