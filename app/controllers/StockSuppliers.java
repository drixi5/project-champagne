package controllers;

import static play.data.Form.form;
import models.SuppliersProducts;
import models.User;
import play.data.Form;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.addProductStock2;
import views.html.stockSupplier;


@Security.Authenticated(Secured.class)
public class StockSuppliers extends Controller {
	
	final static Form<SuppliersProducts> stockForm = form(SuppliersProducts.class);
    
    

    public static Result index() {
    	
        return ok(stockSupplier.render(User.findByEmail(request().username()), SuppliersProducts.find.orderBy("id asc").findList(), form(Quantity.class)));
    }
    
//page view addProductStock    
    public static Result viewAddPStock(){
    	return ok(addProductStock2.render(User.findByEmail(request().username()), stockForm));
    }
    
    
    
    public static Result addProductStock(){
    	Form<SuppliersProducts> form = stockForm.bindFromRequest();
    	
    	if (form.hasErrors()){
			 return badRequest(addProductStock2.render(User.findByEmail(request().username()),form));	 
   
    	}else {
    		 
    		SuppliersProducts stock= form.get();
			 stock.save();
			 return index();
		 }
    }
    
    
    public static Result edit(Long id){
    	SuppliersProducts stock = SuppliersProducts.find.byId(id);
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
    	SuppliersProducts stock = SuppliersProducts.find.byId(id);
    	Form<Quantity> quantityForm = form(Quantity.class).bindFromRequest();
    	
    	if (quantityForm.hasErrors()){
			 return badRequest(stockSupplier.render(User.findByEmail(request().username()), SuppliersProducts.find.orderBy("id asc").findList(), form(Quantity.class)));
		 }
		 else
		 { 
    	addQuantity(id,quantityForm.get().quantity);
		 }
        
        return index();
    	
    }
    


	private static void addQuantity(Long id, int quantity) {
		SuppliersProducts stock = SuppliersProducts.find.byId(id);
		stock.quantity= stock.quantity +quantity;
		stock.edit=false;
		stock.save();
	}
	
	
	public static Result modifyMinus(Long id){
		SuppliersProducts stock = SuppliersProducts.find.byId(id);
    	Form<Quantity> quantityForm = form(Quantity.class).bindFromRequest();
    	
    	if (quantityForm.hasErrors()){
			 return badRequest(stockSupplier.render(User.findByEmail(request().username()), SuppliersProducts.find.orderBy("id asc").findList(), form(Quantity.class)));
		 }
		 else
		 { 
    	minusQuantity(id,quantityForm.get().quantity);
		 }
        
        return index();
    	
    }

	private static void minusQuantity(Long id, int quantity) {
		SuppliersProducts stock = SuppliersProducts.find.byId(id);
		stock.quantity= stock.quantity - quantity;
		stock.edit=false;
		stock.save();
		
	}


	public static Result endModify(Long id){
		SuppliersProducts stock = SuppliersProducts.find.byId(id);
    	stock.edit=false;
    	stock.save();
    	return index();
	}

	public static Result destroy(Long id)  {
		SuppliersProducts stock = SuppliersProducts.find.byId(id);
	 	
            stock.delete();
            return index();
        
    }
	
	
}



