package next.controller;

import next.Controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by granknight on 2016. 7. 11..
 */
public class LoginFormController implements Controller {
    @Override
    public String execute(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;

        return "/user/form.jsp";
    }
}
