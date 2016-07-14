package next;

import com.oracle.jrockit.jfr.RequestDelegate;
import core.web.filter.CharacterEncodingFilter;
import core.web.filter.ResourceFilter;
import next.controller.CreateUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by granknight on 2016. 7. 11..
 */
@WebServlet(name = "dispatcher", urlPatterns = {"", "/"}, loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.debug("insert Servlet!");


        //req.getServletContext().addFilter("resourceFilter", ResourceFilter.class);
        //req.getServletContext().addFilter("characterEncodingFilter", CharacterEncodingFilter.class);

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getRequestURI();
        log.info(path);


        Controller targetController = RequestMapping.getController(path);

        String view = null;


        try {
            if (targetController != null && !path.equals("/")) {
                view = targetController.execute(req, res);
            } else {
                log.error("Controller not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("view url : " + view);

        if (view.contains("redirect:")) {
            view = view.replace("redirect:", "");

            if(view.equals("/")){
                view = "/index.jsp";
            }
            response.sendRedirect(view);
        } else {
            if(view == null){
                view = path;
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);

            dispatcher.forward(req, res);
        }

    }
}
