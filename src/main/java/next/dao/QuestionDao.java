package next.dao;

import java.sql.*;
import java.util.List;

import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import next.model.Answer;
import next.model.Question;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class QuestionDao {

	private static QuestionDao questionDao = new QuestionDao();


	private JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();

	private QuestionDao() {
	}

	public List<Question> findAll() {
		String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
				+ "order by questionId desc";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfAnswer"));
			}
			
		};
		
		return jdbcTemplate.query(sql, rm);
	}

	public Question findById(long questionId) {
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
				+ "WHERE questionId = ?";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"),
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfAnswer"));
			}
		};
		
		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}

	public Integer findAnswerCountById(long questionId) {
		String sql = "SELECT countOfAnswer FROM QUESTIONS "
				+ "WHERE questionId = ?";

		RowMapper<Integer> rm = new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs) throws SQLException {
				return rs.getInt("countOfAnswer");

			}
		};

		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}

	public Question insert(Question question) {
		String sql = "INSERT INTO QUESTIONS (writer, title, createdDate, contents) VALUES (?, ?, ?, ?)";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, question.getWriter());
				pstmt.setString(2, question.getTitle());
				pstmt.setTimestamp(3, new Timestamp(question.getTimeFromCreateDate()));
				pstmt.setString(4, question.getContents());
				return pstmt;
			}
		};

		KeyHolder keyHolder = new KeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		return findById(keyHolder.getId());
	}

	public void delete(Long questionId){
		String sql = "delete from QUESTIONS where questionId=?";

		jdbcTemplate.update(sql, questionId);

	}

	public void increaseAnswerCount(Long questionId){
		String sql = "UPDATE QUESTIONS SET countOfAnswer=countOfAnswer+1 where questionId=?";

		jdbcTemplate.update(sql, questionId);

	}

	public void decreaseAnswerCount(Long questionId){
		String sql = "UPDATE QUESTIONS SET countOfAnswer=countOfAnswer-1 where questionId=?";

		jdbcTemplate.update(sql, questionId);

	}

	public void updateQuestion(Question question){
		String sql = "UPDATE QUESTIONS SET writer=?, title=?, contents=? where questionId=?";
		jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(), question.getQuestionId());

	}

	public static QuestionDao getInstance() {
		return questionDao;
	}
}
