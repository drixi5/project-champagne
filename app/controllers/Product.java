package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.product;

@Security.Authenticated(Secured.class)
public class Product extends Controller {

	public static Result index() {
        return ok(product.render(User.findByEmail(request().username())));
    }
}
