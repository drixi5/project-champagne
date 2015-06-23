package controllers;

import static play.data.Form.form;
import models.Stores;
import models.Suppliers;
import models.User;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.addAdminSupplier;
import views.html.addStore;
import views.html.editStore;
import views.html.supplier;
import views.html.addSupplier;
import views.html.editSupplier;
import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Supplier extends Controller {
	
	
	public static Result index() {
        return ok(supplier.render(User.findByEmail(request().username()), Suppliers.find.orderBy("id asc").findList()));
    }
	final static Form<Suppliers> supplierForm = form(Suppliers.class);
	final static Form<User> usersForm = form(User.class);
	
	 public static Result viewAddSupplier(){
		 Suppliers supplier = new Suppliers(); 
		 
		 return ok(addSupplier.render(User.findByEmail(request().username()),supplierForm, supplier, routes.Supplier.addSupplier()));
	 }
	public static Result addSupplier(){
		Form<Suppliers> form = supplierForm.bindFromRequest();
		 
		 if (form.hasErrors()){
			 Suppliers supplier = new Suppliers();
			 return badRequest(addSupplier.render(User.findByEmail(request().username()),form, supplier, routes.Supplier.addSupplier()));
		 }
		 else
		 { 
			 Suppliers supplier= form.get();
			 supplier.save();
			 Logger.debug(supplier.id+ "supplier");
			 return addAdminSupplier(supplier.id);
		 }
	}
	
	 public static Result addAdminSupplier(Long id) {
         Suppliers lastSupplierAdded = Suppliers.find.byId(id);
         Logger.debug(lastSupplierAdded.id+ "supplier");
         return ok(addAdminSupplier.render(User.findByEmail(request().username()), usersForm, lastSupplierAdded));
     
}
	
	
		 public static Result edit(Long id)  {
		        Suppliers supplier = Suppliers.find.byId(id);
		        
		        return ok(editSupplier.render(User.findByEmail(request().username()),supplierForm.fill(supplier), supplier));
		    }
	
	 public static Result update(Long id)  {
		 	Form<Suppliers> filledForm = supplierForm.bindFromRequest();

	            Suppliers supplier = filledForm.get();
	            supplier.update(id);
	            return index();
	        
	    }
	 
	 public static Result destroy(Long id)  {
		 	Suppliers supplier = Suppliers.find.byId(id);
		 	
	            supplier.delete();
	            return index();
	        
	    }

}

