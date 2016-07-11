package next;

import next.member.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by granknight on 2016. 7. 7..
 */
@WebServlet("/user/create")
public class CreateServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CreateServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Member member = new Member();

        member.setUserId(getParameterValue(req, "userId"));
        member.setPassword(getParameterValue(req, "password"));
        member.setName(getParameterValue(req, "name"));
        member.setEmail(getParameterValue(req, "email"));

        logger.info(member.getUserId());
        logger.info(member.getPassword());
        logger.info(member.getEmail());
        logger.info(member.getName());
        resp.sendRedirect("/index.html");
    }

    private String getParameterValue(HttpServletRequest req, String value) {
        return req.getParameter(value);

    }
}
