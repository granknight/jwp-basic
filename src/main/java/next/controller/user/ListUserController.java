package next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.view.JspView;
import core.view.View;
import next.controller.UserSessionUtils;
import next.dao.UserDao;

public class ListUserController implements Controller {
	@Override
	public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (!UserSessionUtils.isLogined(req.getSession())) {
			return new JspView("redirect:/users/loginForm");
//			return "redirect:/users/loginForm";
		}
		
		UserDao userDao = new UserDao();
		req.setAttribute("users", userDao.findAll());
		return new JspView("/user/list.jsp");
//		return "/user/list.jsp";
	}
}
