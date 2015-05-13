package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.product;
import views.html.addProduct;

@Security.Authenticated(Secured.class)
public class Product extends Controller {

	public static Result index() {
        return ok(product.render(User.findByEmail(request().username())));
    }
	public static Result addProduct(){
		return ok(addProduct.render(User.findByEmail(request().username())));
	}

	
}
