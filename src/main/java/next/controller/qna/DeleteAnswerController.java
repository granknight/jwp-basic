package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;
import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class DeleteAnswerController extends AbstractController {
    private AnswerDao answerDao = AnswerDao.getInstance();
	private QuestionDao questionDao = QuestionDao.getInstance();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String answerId = request.getParameter("answerId");
		String questionId = request.getParameter("questionId");
		Long parsedAnswerId = Long.parseLong(answerId);
		Long parsedQuestionId = Long.parseLong(questionId);
		ModelAndView mav = jsonView();
		try {
			answerDao.delete(parsedAnswerId);
			questionDao.decreaseAnswerCount(parsedQuestionId);
			mav.addObject("result", Result.ok());
			mav.addObject("answerId", parsedAnswerId);
		} catch (DataAccessException e) {
			mav.addObject("result", Result.fail(e.getMessage()));
		}
		return mav;
	}
}
