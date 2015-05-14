package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.supplier;
import views.html.addSupplier;
import models.ModelProduct;

@Security.Authenticated(Secured.class)
public class Supplier extends Controller {
	
	public static Result index() {
        return ok(supplier.render(User.findByEmail(request().username())));
    }
	public static Result addSupplier(){
		return ok(addSupplier.render(User.findByEmail(request().username())));
	}
}
