package controllers;



import models.User;
import models.Stores;
import play.mvc.*;
import play.data.Form;
import views.html.store;
import views.html.addStore;
import views.html.addAdmin;
import views.html.editStore;
import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Store extends Controller {
	

	 public static Result index() {
	        return ok(store.render(User.findByEmail(request().username()), Stores.find.orderBy("id asc").findList()));
	    }
	  
	 final static Form<Stores> storeForm = form(Stores.class);
	 
	 public static Result viewAddStore(){
		 Stores store = new Stores();
		 
		 return ok(addStore.render(User.findByEmail(request().username()),storeForm, store, routes.Store.addStore()));
	 }
	 	 
	 
	 
	 public static Result addStore(){
		 Form<Stores> form = storeForm.bindFromRequest();
		 
		 if (form.hasErrors()){
			 Stores store = new Stores();
			 return badRequest(addStore.render(User.findByEmail(request().username()),form, store, routes.Store.addStore()));
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
	 
	 public static Result edit(Long id)  {
	        Stores store = Stores.find.byId(id);
	        
	        return ok(editStore.render(User.findByEmail(request().username()),storeForm.fill(store), store));
	    }
	 
	 
	 public static Result update(Long id)  {
		 	Form<Stores> filledForm = storeForm.bindFromRequest();

	        /*if(filledForm.hasErrors())  {
	            //can't pull out of the form if there are errors
	            Book book = Book.find.byId(id);
	            return ok(edit.render(filledForm, book));
	        } else {*/
	            Stores store = filledForm.get();
	            store.update(id);
	            return index();
	        
	    }
	 
	 public static Result destroy(Long id)  {
		 	Stores store = Stores.find.byId(id);
		 	
	            store.delete();
	            return index();
	        
	    }

}
