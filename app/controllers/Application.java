package controllers;

import models.Suppliers;
import models.User;
import models.TypeUser;
import models.Stores;
import models.utils.AppException;
import play.Logger;
import play.data.Form;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import static play.data.Form.form;

/**
 * Login and Logout.
 * User: yesnault
 */
public class Application extends Controller {

    public static Result GO_HOME = redirect(
            routes.Application.index()
    );

    public static Result GO_DASHBOARD = redirect(
            routes.Dashboard.index()
    );
    
    public static Result GO_STORE = redirect(
            routes.Store.index()
    );

    public static Result GO_STOCKSUPPLIER = redirect(
            routes.StockSuppliers.index()
    );
    /**
     * Display the login page or dashboard if connected
     *
     * @return login page or dashboard
     */
    public static Result index() {
        // Check that the email matches a confirmed user before we redirect
        String email = ctx().session().get("email");
        if (email != null) {
            User user = User.findByEmail(email);
            if (user != null && user.validated) {
            	if( user.typeUser.id == 1){
            		return GO_STORE;
            	} else if (user.typeUser.id == 5){
            		return GO_STOCKSUPPLIER;
            	}else{
                return GO_DASHBOARD;
            	}
            } else {
                Logger.debug("Clearing invalid session credentials");
                session().clear();
            }
        }

        return ok(index.render(form(Register.class), form(Login.class)));
    }

    /**
     * Login class used by Login Form.
     */
    public static class Login {

        @Constraints.Required
        public String email;
        @Constraints.Required
        public String password;

        /**
         * Validate the authentication.
         *
         * @return null if validation ok, string with details otherwise
         */
        public String validate() {

            User user = null;
            try {
                user = User.authenticate(email, password);
            } catch (AppException e) {
                return Messages.get("Erreur technique");
            }
            if (user == null) {
                return Messages.get("Login ou mot de passe invalide");
            } else if (!user.validated) {
                return Messages.get("Votre compte n'a pas été validé. Veuillez vérifier vos mails.");
            }
            return null;
        }

    }

    public static class Register {

        @Constraints.Required
        public String email;

        @Constraints.Required
        public String lastname;
        
        @Constraints.Required
        public String firstname;

        @Constraints.Required
        public String inputPassword;
        
        public TypeUser typeUser;
        
        public Stores store;
        
        public Suppliers supplier;

        /**
         * Validate the authentication.
         *
         * @return null if validation ok, string with details otherwise
         */
        public String validate() {
            if (isBlank(email)) {
                return "Adresse mail obligatoire";
            }

            if (isBlank(lastname)) {
                return "Nom obligatoire";
            }
            
            if (isBlank(firstname)) {
                return "Prénom obligatoire";
            }

            if (isBlank(inputPassword)) {
                return "Mot de passe obligatoire";
            }

            return null;
        }

        private boolean isBlank(String input) {
            return input == null || input.isEmpty() || input.trim().isEmpty();
        }
    }

    /**
     * Handle login form submission.
     *
     * @return Dashboard if auth OK or login form if auth KO
     */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();

        Form<Register> registerForm = form(Register.class);

        if (loginForm.hasErrors()) {
            return badRequest(index.render(registerForm, loginForm));
        } else {
            session("email", loginForm.get().email);
            return GO_DASHBOARD;
        }
    }

    /**
     * Logout and clean the session.
     *
     * @return Index page
     */
    public static Result logout() {
        session().clear();
        flash("success", Messages.get("Vous avez été déconnecté"));
        return GO_HOME;
    }

}