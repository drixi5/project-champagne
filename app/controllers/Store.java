package controllers;



import models.User;
import models.Stores;
import play.mvc.*;
import play.data.Form;
import views.html.store;
import views.html.addStore;
import views.html.addAdmin;
import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Store extends Controller {
	

	 public static Result index() {
	        return ok(store.render(User.findByEmail(request().username()), Stores.find.orderBy("id asc").findList()));
	    }
	  
	 final static Form<Stores> storeForm = form(Stores.class);
	 
	 public static Result viewAddStore(){
		 return ok(addStore.render(User.findByEmail(request().username()),storeForm));
	 }
	 	 
	 
	 
	 public static Result addStore(){
		 Form<Stores> form = storeForm.bindFromRequest();
		 
		 if (form.hasErrors()){
			 return badRequest(addStore.render(User.findByEmail(request().username()),form));
		 }
		 else
		 { 
			 Stores store= form.get();
			 store.save();
			 //return ok(addAdmin.render(User.findByEmail(request().username())));
			 return index();
		 }
	 } 	 
		 
/* A voir avec Xav au niveau du model.user ! 	 
	 public static Result addAdmin(){
		 User admin = Form.form(User.class).bindFromRequest().get();
   	  	 admin.save();
   	     return index();
	 }*/
}
