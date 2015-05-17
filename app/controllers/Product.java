package controllers;

import models.User;
import models.ModelProduct;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.data.Form;
import play.db.ebean.Model;

import views.html.product;
import views.html.addProduct;

import java.util.List;

import static play.libs.Json.toJson;

@Security.Authenticated(Secured.class)
public class Product extends Controller {

	public static Result index() {
        return ok(product.render(User.findByEmail(request().username())));
    }
	
	public static Result addProduct(){
		return ok(addProduct.render(User.findByEmail(request().username())));
	}
	
	public static Result saveProduct(){
		ModelProduct product = Form.form(ModelProduct.class).bindFromRequest().get();
		product.save();
		return redirect(routes.Product.index());
	}
	
	public static Result getProducts(){
		List<Product> products = new Model.Finder(String.class, ModelProduct.class).all();
		return ok(toJson(products));
	}
	
}
