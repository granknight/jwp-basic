package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by granknight on 2016. 7. 21..
 */
public class UpdateFormController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(UpdateFormController.class);

    private QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String questionId = request.getParameter("questionId");
        Question question = questionDao.findById(Long.parseLong(questionId));


        ModelAndView mv = jspView("/qna/updateForm.jsp");

        mv.addObject("question", question);

        return mv;
    }
}
