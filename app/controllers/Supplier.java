package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.supplier;

@Security.Authenticated(Secured.class)
public class Supplier extends Controller {
	
	public static Result index() {
        return ok(supplier.render(User.findByEmail(request().username())));
    }
}
