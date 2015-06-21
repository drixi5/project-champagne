package controllers;



import static play.data.Form.form;
import models.Products;
import models.Suppliers;
import models.User;
import play.mvc.*;
import play.data.Form;
import play.db.ebean.Model;
import views.html.addEmployee;
import views.html.employee;
import views.html.supplier;
import views.html.addSupplier;

import java.util.List;

import static play.data.Form.form;
import static play.libs.Json.toJson;


@Security.Authenticated(Secured.class)
public class Supplier extends Controller {
	
	private static Object find;


	public static Result index() {
        return ok(supplier.render(User.findByEmail(request().username()), Suppliers.find.orderBy("id asc").findList()));
    }
final static Form<Suppliers> SupplierForm = form(Suppliers.class);
	
	public static Result addSupplier() {
        return ok(addSupplier.render(User.findByEmail(request().username()), SupplierForm));
    }
	
	public static Result saveSupplier(){
		Form<Suppliers> form = SupplierForm.bindFromRequest();
		Suppliers supplier = form.get();
		supplier.save();
		return index();
	}	
	
	public static Result getSupplier(){
		List<Suppliers> supplier = new Model.Finder(String.class, Suppliers.class).all();
		return ok(toJson(supplier));
	}
}