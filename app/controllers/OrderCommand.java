package controllers;

import models.StoresProducts;
import models.Stores;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.orders.orderCommand;

@Security.Authenticated(Secured.class)
public class OrderCommand extends Controller {

	public static Result index() {
        return ok(orderCommand.render(User.findByEmail(request().username()), StoresProducts.find.orderBy("product_id asc").findList()));
    }
}
