package controllers;

import static play.data.Form.form;

import javax.validation.*;

import models.Stores;
import models.User;
import models.Products;
import models.StoresProducts;
import models.Unit;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.dashboard.index;
import views.html.addProductStock;

@Security.Authenticated(Secured.class)
public class Dashboard extends Controller {

    public static Result index() {
        return ok(index.render(User.findByEmail(request().username()), StoresProducts.find.orderBy("id asc").findList()));
    }
    
    final static Form<StoresProducts> stockForm = form(StoresProducts.class);
    
    public static Result viewAddPStock(){
    	return ok(addProductStock.render(User.findByEmail(request().username()), stockForm));
    }
    
    
    
    public static Result addProductStock(){
    	Form<StoresProducts> form = stockForm.bindFromRequest();
    	
    	if (form.hasErrors()){
			 return badRequest(addProductStock.render(User.findByEmail(request().username()),form));
		 }
		 else
		 { 
			 
			 StoresProducts stock= form.get();
			 stock.save();
			 return index();
		 }	 
    }
}
