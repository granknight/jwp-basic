package next;

import core.web.filter.ResourceFilter;
import next.controller.CreateUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by granknight on 2016. 7. 11..
 */
@WebServlet(name="dispatcher", urlPatterns = {"", "/"}, loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet{

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.debug("insert Servlet!");
    }
}
