package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controllers.Dashboard.Quantity;
import models.OrderStatus;
import models.Orders;
import models.StoresProducts;
import models.Stores;
import models.Suppliers;
import models.SuppliersProducts;
import models.User;
import play.Logger;
import play.data.Form;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.addProductStock;
import views.html.orders.orderCommand;
import views.html.orders.orderInProgress;
import views.html.orders.viewStore;
import views.html.orders.viewSupplier;
import views.html.chooseSupplier;

@Security.Authenticated(Secured.class)
public class OrderCommand extends Controller {

	final static Form<Orders> orderForm = form(Orders.class);
	
	public static Result orderCommand(Long id) {
		List<Orders> orders = Orders.find.all();
		
        return ok(orderCommand.render(User.findByEmail(request().username()), StoresProducts.find.orderBy("product.typeProduct.name asc").where().eq("store_id", id).findList(), orderForm ));
    }
	

	public static Result command(Long id){
		Form<Orders> form = orderForm.bindFromRequest();

    	if (form.hasErrors()){
			 return badRequest(orderCommand.render(User.findByEmail(request().username()), StoresProducts.find.orderBy("product_id asc").where().eq("store_id", id).findList(), orderForm ));	 
    	
    	}else {	 
    		Orders order = form.get();
			order.save();
			return orderCommand(id);
		 }
		
	}
	
	
	
	
	public static Result orderInProgress(Long id) {
		User user = User.find.byId(id);
		if(user.typeUser.id == 2){
			Logger.debug(user.store.id+" store");
			return ok(orderInProgress.render(User.findByEmail(request().username()), Orders.find.orderBy("creationDate desc").orderBy("status asc").where("status != 4").where().eq("store_id", user.store.id).findList()));
		}else {
			
			return ok(orderInProgress.render(User.findByEmail(request().username()), Orders.find.orderBy("creationDate desc").orderBy("store_id asc").orderBy("status asc").where("status != 4 && status !=1").where().findList()));
		}	
    }
	
	public static Result orderHandle(Long id){
		User user = User.find.byId(id);
		if (user.typeUser.id == 1){
			return ok(orderInProgress.render(User.findByEmail(request().username()), Orders.find.orderBy("creationDate desc").orderBy("store_id asc").where("status =1").findList()));
		}
		else{
			return ok(orderInProgress.render(User.findByEmail(request().username()), Orders.find.orderBy("creationDate desc").orderBy("store_id asc").where("status =2").where().eq("supplier_id", user.supplier.id).findList()));
		}
	}
	
	public static Result orderValid(Long id) {
		User user = User.find.byId(id);
		if (user.typeUser.id == 1){
			return ok(orderInProgress.render(User.findByEmail(request().username()), Orders.find.orderBy("creationDate desc").orderBy("store_id asc").where("status = 4").findList()));
		}
		else if (user.typeUser.id  == 2){
			return ok(orderInProgress.render(User.findByEmail(request().username()), Orders.find.orderBy("creationDate desc").where("status = 4").where().eq("store_id", user.store.id).findList()));
		}
		else {
			return ok(orderInProgress.render(User.findByEmail(request().username()), Orders.find.orderBy("creationDate desc").where("status = 4 || status =3").where().eq("supplier_id", user.supplier.id).findList()));
		}
    }
	

	
	public static Result validSend(Long id, Long id2, Long id3){
		modifStatus(id, (long) 3);
		minusStockSupplier(id, id2);
		return orderHandle(id3);
	}
	
	public static Result validReceipt(Long id, Long id2, Long id3){
		modifStatus(id, (long) 4);
		addStockStore(id,id2);
		addDate(id);
		return orderInProgress(id3);
	}
	
	public static void modifStatus(Long id, Long id2){
		Orders order = Orders.find.byId(id);
		OrderStatus status = OrderStatus.find.byId(id2); 
		order.status = status;
	//	Logger.debug(order.status+" statut final");
		order.save();
	}

	public static void addDate(Long id){
		Orders order = Orders.find.byId(id);
		order.delivryDate = Calendar.getInstance().getTime();
		order.save();
	}
	
	public static void addStockStore(Long id, Long id2){
		Orders order = Orders.find.byId(id);
		StoresProducts stock = StoresProducts.find.byId(id2);
	//	Logger.debug(stock.id+" stock");
	//	Logger.debug(stock.quantity+" quantité initial");
	//	Logger.debug(order.quantity+" quantité a rajouter");
		stock.quantity += order.quantity;
	//	Logger.debug(stock.quantity+" quantité finale");
		stock.save();
	}
	
	public static void minusStockSupplier(Long id, Long id2){
		Orders order = Orders.find.byId(id);
		SuppliersProducts stock = SuppliersProducts.find.byId(id2);
		Logger.debug(stock.id+" stock");
		Logger.debug(stock.quantity+" quantité initial");
		Logger.debug(order.quantity+" quantité a rajouter");
		stock.quantity -= order.quantity;
		Logger.debug(stock.quantity+" quantité finale");
		stock.save();
	}

	
	public static Result destroy(Long id, Long id2)  {
		Orders order = Orders.find.byId(id2);
            order.delete();
            return orderInProgress(id);
        
    }
	
	
	public static Result viewStore(Long id){
		return ok(viewStore.render(User.findByEmail(request().username()), Stores.find.byId(id)));
	}
	
	public static Result viewSupplier(Long id){
		return ok(viewSupplier.render(User.findByEmail(request().username()), Suppliers.find.byId(id)));
	}
	
	public static Result chooseSupplier(Long id){
		Orders order = Orders.find.byId(id);
		return ok(chooseSupplier.render(User.findByEmail(request().username()), Orders.find.byId(id), SuppliersProducts.find.orderBy("supplier_id asc").where().eq("product_id", order.storesProducts.product.id ).findList()));
	}
	
	public static Result validSupplier(Long id, Long id2, Long id3){
		modifStatus(id2, (long) 2);
		validSupplier2(id2,id3);
		return orderHandle(id);
	}
	
	
	public static void validSupplier2(Long id, Long id2){
		Orders order = Orders.find.byId(id);
		SuppliersProducts supplierStock = SuppliersProducts.find.byId(id2);
	//	Logger.debug(order.id+" order");
	//	Logger.debug(supplierStock.id+" supplierStock finale");
		order.supplier = supplierStock.supplier;
		order.suppliersProducts = supplierStock;
		order.save();
	}
}
