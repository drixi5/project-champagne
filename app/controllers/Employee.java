package controllers;

import controllers.account.Signup;
import models.Products;
import models.TypeUser;
import models.Stores;
import models.User;
import play.Logger;
import play.mvc.*;
import play.data.Form;
import play.db.ebean.Model;

import java.util.List;

import views.html.editStore;
import views.html.employee;
import views.html.addEmployee;
import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Employee extends Controller {
	
	public static Result index() {
        return ok(employee.render(User.findByEmail(request().username()), User.find.orderBy("id asc").findList()));
    }
	
	final static Form<User> usersForm = form(User.class);
	
	public String generatePassword(int length) {
    	String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    String pass = "";
	    for(int x=0;x<length;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 62);
	       pass += chars.charAt(i);
	    }
	    return pass;
    }
	
	public static Result addEmployee() {
        return ok(addEmployee.render(User.findByEmail(request().username()), usersForm, controllers.account.routes.Signup.save()));
    }
	
	/*public static Result edit(Long id)  {
        User employee = User.find.byId(id);
        
        return ok(editEmployee.render(User.findByEmail(request().username()),usersForm.fill(employee), employee));
    }*/
 
 
	public static Result update(Long id)  {
	 	Form<User> filledForm = usersForm.bindFromRequest();

            User employee = filledForm.get();
            employee.update(id);
            return index();
        
    }
	
	public static Result destroy(Long id)  {
	 	User user = User.find.byId(id);
	 	
            user.delete();
            return index();
        
    } 
}
