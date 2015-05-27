package controllers;

import models.Products;
import models.TypeUser;
import models.Stores;
import models.User;
import play.mvc.*;
import play.data.Form;
import play.db.ebean.Model;

import java.util.List;

import views.html.employee;
import views.html.addEmployee;

import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Employee extends Controller {
	
	public static Result index() {
        return ok(employee.render(User.findByEmail(request().username()), User.find.orderBy("id asc").findList()));
    }
	
	final static Form<User> usersForm = form(User.class);
	
	public static Result addEmployee() {
        return ok(addEmployee.render(User.findByEmail(request().username()), usersForm));
    }
}
