package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.store;

@Security.Authenticated(Secured.class)
public class Store extends Controller {

	 public static Result index() {
	        return ok(store.render(User.findByEmail(request().username())));
	    }
}
