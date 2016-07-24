package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by granknight on 2016. 7. 21..
 */
public class UpdateQuestionController extends AbstractController{

    private QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String questionId = request.getParameter("questionId");
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");


        Question question= null;
        if(questionId != null)
            question = new Question(Long.parseLong(questionId), writer, title, contents);

        questionDao.updateQuestion(question);

        return jspView("redirect:/qna/show?questionId="+ questionId);
    }
}
