package controllers;

import static play.data.Form.form;

import javax.validation.*;

import controllers.Application.Login;
import models.Stores;
import models.User;
import models.Products;
import models.StoresProducts;
import models.Unit;
import play.data.Form;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.dashboard.index;
import views.html.addProductStock;

@Security.Authenticated(Secured.class)
public class Dashboard extends Controller {
	
	final static Form<StoresProducts> stockForm = form(StoresProducts.class);

    public static Result index() {
        return ok(index.render(User.findByEmail(request().username()), StoresProducts.find.orderBy("id asc").findList(), form(Quantity.class)));
    }
    
    
    public static Result viewAddPStock(){
    	return ok(addProductStock.render(User.findByEmail(request().username()), stockForm));
    }
    
    
    
    public static Result addProductStock(){
    	Form<StoresProducts> form = stockForm.bindFromRequest();
    	
    	if (form.hasErrors()){
			 return badRequest(addProductStock.render(User.findByEmail(request().username()),form));
		 
    	}else if(form.get().threshold_max<form.get().threshold_min){
    		 return badRequest(addProductStock.render(User.findByEmail(request().username()),form));
    	
    	}else { 
			 
			 StoresProducts stock= form.get();
			 stock.save();
			 return index();
		 }
    }
    
   
 	
    
    
    
    public static Result edit(Long id){
    	StoresProducts stock = StoresProducts.find.byId(id);
    	stock.edit=true;
    	stock.save();
    	return index();
    }
    
    
    public static class Quantity {
    	
    	@Constraints.Required
    	@Formats.NonEmpty
        public int quantity;
    	
    }
    
    
    public static Result modifyAdd(Long id){
    	StoresProducts stock = StoresProducts.find.byId(id);
    	Form<Quantity> quantityForm = form(Quantity.class).bindFromRequest();
    	
    	if (quantityForm.hasErrors()){
			 return badRequest(index.render(User.findByEmail(request().username()), StoresProducts.find.orderBy("id asc").findList(), form(Quantity.class)));
		 }
		 else
		 { 
    	addQuantity(id,quantityForm.get().quantity);
		 }
        
        return index();
    	
    }
    


	private static void addQuantity(Long id, int quantity) {
		StoresProducts stock = StoresProducts.find.byId(id);
		stock.quantity= stock.quantity +quantity;
		stock.edit=false;
		stock.save();
	}
	
	
	public static Result modifyMinus(Long id){
    	StoresProducts stock = StoresProducts.find.byId(id);
    	Form<Quantity> quantityForm = form(Quantity.class).bindFromRequest();
    	
    	if (quantityForm.hasErrors()){
			 return badRequest(index.render(User.findByEmail(request().username()), StoresProducts.find.orderBy("id asc").findList(), form(Quantity.class)));
		 }
		 else
		 { 
    	minusQuantity(id,quantityForm.get().quantity);
		 }
        
        return index();
    	
    }

	private static void minusQuantity(Long id, int quantity) {
		StoresProducts stock = StoresProducts.find.byId(id);
		stock.quantity= stock.quantity - quantity;
		stock.edit=false;
		stock.save();
		
	}


	public static Result endModify(Long id){
		StoresProducts stock = StoresProducts.find.byId(id);
    	stock.edit=false;
    	stock.save();
    	return index();
	}

	public static Result destroy(Long id)  {
	 	StoresProducts stock = StoresProducts.find.byId(id);
	 	
            stock.delete();
            return index();
        
    }
	
	
}
