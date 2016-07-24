package next.controller.qna;

import core.mvc.ModelAndView;
import next.model.Result;

/**
 * Created by granknight on 2016. 7. 25..
 */
public class DefaultDeleteQuestionController extends DeleteQuestionController{
    @Override
    public ModelAndView getView(Result result) {
        ModelAndView mv = jspView("redirect:/");
        return mv;
    }
}
