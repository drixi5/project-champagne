package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.orders.orderToHandle;

@Security.Authenticated(Secured.class)
public class OrderToHandle extends Controller {

	public static Result index() {
        return ok(orderToHandle.render(User.findByEmail(request().username())));
    }
}
