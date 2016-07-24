package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
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
public class ApiDeleteQuestionController extends DeleteQuestionController {


    @Override
    public ModelAndView getView(Result result) {
        ModelAndView view = jsonView();

        if(result == Result.ok()){
            view.addObject("result", Result.ok());
        }else{
            view.addObject("result", Result.fail(result.getMessage()));
        }
        return view;
    }
}
