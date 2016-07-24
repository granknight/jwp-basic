package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by granknight on 2016. 7. 21..
 */
public class CreateQuestionController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(CreateQuestionController.class);

    private QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        String loginName = "";

        if(user != null){
            loginName = user.getName();
        }else{
            return jspView("redirect:/?login=notlogin");
        }

        Question question = new Question(loginName,
                request.getParameter("title"),
                request.getParameter("contents"));
        log.debug("question : {}", question);

        questionDao.insert(question);

        return jspView("redirect:/");


//        Long questionId = Long.parseLong(req.getParameter("questionId"));
//
//        question = questionDao.findById(questionId);
//        answers = answerDao.findAllByQuestionId(questionId);
//
//        ModelAndView mav = jspView("/qna/show.jsp");
//        mav.addObject("question", question);
//        mav.addObject("answers", answers);
//        return mav;
    }
}
