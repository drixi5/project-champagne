package controllers;

import models.User;
import models.Stores;
import play.mvc.*;
import play.mvc.Result;
import play.mvc.Security;
import views.html.store;
import views.html.addStore;
import play.data.Form;


@Security.Authenticated(Secured.class)
public class Store extends Controller {

	 public static Result index() {
	        return ok(store.render(User.findByEmail(request().username())));
	    }
	 
	 public static Result viewAddStore(){
		 return ok(addStore.render(User.findByEmail(request().username())));
	 }
	 
	 public static Result addStore(){
		 Stores store= Form.form(Stores.class).bindFromRequest().get();
   	  	 store.save();
   	     return index();
	 }
}
