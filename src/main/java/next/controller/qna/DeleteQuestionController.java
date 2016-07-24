package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by granknight on 2016. 7. 25..
 */
public abstract class DeleteQuestionController extends AbstractController {

    QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Long questionId = Long.parseLong(request.getParameter("questionId"));

        int countAnswer = questionDao.findAnswerCountById(questionId);

        Question question = questionDao.findById(questionId);

        HttpSession session = request.getSession();

        User loginUser = (User) session.getAttribute("user");

        ModelAndView mv = null;

        if(countAnswer < 1 && loginUser.getName().equals(question.getWriter())){
            try {
                questionDao.delete(questionId);
                mv = getView(Result.ok());
            }catch(Exception e){
                mv = getView(Result.fail("failed"));
            }
        }else{
            mv = getView(Result.fail("cantAccess"));
        }

        return mv;
    }

    public abstract ModelAndView getView(Result result);

}
