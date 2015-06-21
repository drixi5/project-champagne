package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import controllers.Dashboard.Quantity;
import models.Meals;
import models.MealsProducts;
import models.StoresProducts;
import models.TypeMeal;
import models.User;
import play.data.Form;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.mvc.*;
import views.html.addMealProducts;
import views.html.addMeals;
import views.html.meal;
import views.html.dashboard.index;
import play.Logger;

@Security.Authenticated(Secured.class)
public class Meal extends Controller {
	
//page view addMeals	
	final static Form<Meals> mealForm = form(Meals.class);

	 public static Result addMeal(){
		 
		 return ok(addMeals.render(User.findByEmail(request().username()),mealForm));
	 }
	 
	 public static Result saveMeal(){
		 
		 Form<Meals> form = mealForm.bindFromRequest();

		 if (form.hasErrors()){

			 return badRequest(addMeals.render(User.findByEmail(request().username()),form));
		 }
		 else
		 { 
			 Meals meal= form.get();	
			 meal.save();
			 return addMealProduct(meal.id);
			
		 }
	 } 
	 
	 
//page view addMealProducts
	 	 
final static Form<MealsProducts> mealProdForm = form(MealsProducts.class); 
	 
 public static Result addMealProduct(Long id){

		 return ok(addMealProducts.render(User.findByEmail(request().username()),mealProdForm, Meals.find.byId(id), MealsProducts.find.orderBy("id asc").findList(), StoresProducts.find.orderBy("id asc").findList()));
	 }


 public static Result saveMealProduct(Long id){
	 
	 Form<MealsProducts> form = mealProdForm.bindFromRequest();

	 if (form.hasErrors()){

		 return badRequest(addMealProducts.render(User.findByEmail(request().username()),form,Meals.find.byId(id), MealsProducts.find.orderBy("id asc").findList(), StoresProducts.find.orderBy("id asc").findList()));
	 }
	 else
	 { 
		 MealsProducts mealProd= form.get();	
		 mealProd.save();
		 return addMealProduct(id);
		
	 }
 }
 
 public static class Quantity {
		
		@Constraints.Required
	    public int quantity;
		
	}

	final static Form<Quantity> quantityForm = form(Quantity.class);
 
 //page view dessert
//page view entree
//page view meal
public static Result meal(Long id) {
	
  return ok(meal.render(User.findByEmail(request().username()), MealsProducts.find.orderBy("id asc").findList(), Meals.find.orderBy("id asc").findList(), StoresProducts.find.orderBy("id asc").findList(), quantityForm,TypeMeal.find.byId(id)));
}




public static Result decrement(Long id , Long id2){
	Meals meals = Meals.find.byId(id);
	Form<Quantity> form = quantityForm.bindFromRequest();
	
	if (form.hasErrors()){
		 return badRequest(meal.render(User.findByEmail(request().username()), MealsProducts.find.orderBy("id asc").findList(), Meals.find.orderBy("id asc").findList(), StoresProducts.find.orderBy("id asc").findList(),form, TypeMeal.find.byId(id2)));
	 }
	 else
	 { 
	minusQuantity(id,form.get().quantity);
	 }
    
    return meal(id2);
}

private static void minusQuantity(Long id, int quantity) {
	Meals meals =  Meals.find.byId(id);
	List<MealsProducts> mealProd = MealsProducts.find.all();
	List<StoresProducts> stock = StoresProducts.find.all();
    for(MealsProducts mp : mealProd){	
    	if(mp.meal.id==id){
    		for(StoresProducts s : stock)
    		{
    			if (s.product.id == mp.product.id)
    			{
    				s.quantity += -1*quantity*mp.quantity; 
  
    				s.save();
    				
    			}
    		}
    	}
   }
    
}


}
