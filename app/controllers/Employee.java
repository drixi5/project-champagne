package controllers;

import models.User;
import play.mvc.*;
import play.data.Form;
import views.html.employee;
import views.html.addEmployee;

@Security.Authenticated(Secured.class)
public class Employee extends Controller {
	
	public static Result index() {
        return ok(employee.render(User.findByEmail(request().username())));
    }
	
	public static Result addEmployee() {
        return ok(addEmployee.render(User.findByEmail(request().username())));
    }
}
