package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.orders.orderInProgress;

@Security.Authenticated(Secured.class)
public class OrderInProgress extends Controller {

	public static Result index() {
        return ok(orderInProgress.render(User.findByEmail(request().username())));
    }
}
