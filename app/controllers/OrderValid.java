package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.orders.orderValid;

@Security.Authenticated(Secured.class)
public class OrderValid extends Controller {

	public static Result index() {
        return ok(orderValid.render(User.findByEmail(request().username())));
    }
}
