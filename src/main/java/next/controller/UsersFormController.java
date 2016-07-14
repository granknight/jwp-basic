package next.controller;

import next.Controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by granknight on 2016. 7. 11..
 */
public class UsersFormController implements Controller {
    @Override
    public String execute(ServletRequest request, ServletResponse response) throws Exception {
        return "/user/form.jsp";
    }
}
