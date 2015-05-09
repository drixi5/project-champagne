package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.store.index;

public class Store extends Controller {

	 public static Result index() {
	        return ok(index.render(User.findByEmail(request().username())));
	    }
}
