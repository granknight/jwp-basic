package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Authenticator;
import core.mvc.Controller;
import core.view.JsonView;
import core.view.View;
import next.dao.AnswerDao;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by granknight on 2016. 7. 18..
 */
public class DeleteAnswerController implements Controller{
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Long answerId = Long.parseLong(req.getParameter("answerId"));
        AnswerDao answerDao = new AnswerDao();

        answerDao.delete(answerId);

        return new JsonView();
    }
}
