package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.model.User;

public class ShowController extends AbstractController {
	private QuestionDao questionDao = QuestionDao.getInstance();
	private AnswerDao answerDao = AnswerDao.getInstance();

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		Long questionId = Long.parseLong(req.getParameter("questionId"));

		Question question = questionDao.findById(questionId);
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);

		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("user");
		ModelAndView mav;

		if(user==null || question.getWriter().equals(user.getName())){

			mav = jspView("/qna/show.jsp");
		}else {
			mav = jspView("/qna/show.jsp?login=notmatched");
		}


		mav.addObject("question", question);
		mav.addObject("answers", answers);
		return mav;
	}
}
