package next;

import next.controller.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by granknight on 2016. 7. 11..
 */
public class RequestMapping {

    private static Map<String, Controller> controllers = new HashMap<String, Controller>();

    static {
        controllers.put("/users/create", new CreateUserController());
        controllers.put("/users/form", new UsersFormController());
        controllers.put("/home", new HomeController());
        controllers.put("/users", new ListUserController());
        controllers.put("/users/login", new LoginController());
        controllers.put("/users/loginForm", new LoginFormController());
        controllers.put("/users/logout", new LogoutController());
        controllers.put("/users/profile", new ProfileController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/updateForm", new UpdateFormController());
    }

    public static Controller getController(String requestUrl) {
        return controllers.get(requestUrl);
    }


}
