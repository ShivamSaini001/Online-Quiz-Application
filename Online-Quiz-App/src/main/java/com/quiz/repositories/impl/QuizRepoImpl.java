package com.quiz.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quiz.entities.QuizResult;
import com.quiz.repositories.QuizRepo;
import com.quiz.repositories.mappers.QuizResultRowMapper;

@Repository
public class QuizRepoImpl implements QuizRepo {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public QuizRepoImpl() {
	}

	@Override
	public int saveQuizResult(QuizResult quizResult) {

		String query = "INSERT INTO quiz_result VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int rowsAffected = jdbcTemplate.update(query, quizResult.getQuizId(), quizResult.getUserId(),
				quizResult.getCategory().getCategoryId(), quizResult.getTotalQuestions(),
				quizResult.getNumberOfCorrectAnswer(), quizResult.getNumberOfWrongAnswer(),
				quizResult.getNumberOfNotAttemptQuestions(), quizResult.getMarkForEachQuestion(),
				quizResult.getTotalMarks(), quizResult.getObtainedMarkes(), quizResult.getScoreInPercentage(),
				quizResult.getDate().toString(), quizResult.getTime().toString());
		return rowsAffected;
	}

	@Override
	public List<QuizResult> getAllQuizResults() {
		String query = "SELECT quiz_id, username, total_questions, "
				+ "correct_answers, wrong_answers, not_attempt, mark_for_each_question, "
				+ "total_marks, obtained_markes, score_in_percentage, created_at_date, "
				+ "created_at_time, qc.category_id, qc.category_name " + "FROM quiz_result qr "
				+ "LEFT JOIN question_categories qc " + "ON qr.category_id = qc.category_id ORDER BY qr.quiz_id DESC";

		List<QuizResult> AllQuizResults = jdbcTemplate.query(query, new QuizResultRowMapper());
		return AllQuizResults;
	}

	@Override
	public List<QuizResult> getAllQuizResultsByUserId(String email) {
		String query = "SELECT quiz_id, username, total_questions, "
				+ "correct_answers, wrong_answers, not_attempt, mark_for_each_question, "
				+ "total_marks, obtained_markes, score_in_percentage, created_at_date, "
				+ "created_at_time, qc.category_id, qc.category_name " + "FROM quiz_result qr "
				+ "LEFT JOIN question_categories qc " + "ON qr.category_id = qc.category_id " + "WHERE username = ? ORDER BY qr.quiz_id DESC";

		List<QuizResult> AllQuizResults = jdbcTemplate.query(query, new QuizResultRowMapper(), email);
		return AllQuizResults;
	}

	@Override
	public QuizResult getQuizResultById(int quizId) {
		String query =  "SELECT quiz_id, username, total_questions, "
				+ "correct_answers, wrong_answers, not_attempt, mark_for_each_question, "
				+ "total_marks, obtained_markes, score_in_percentage, created_at_date, "
				+ "created_at_time, qc.category_id, qc.category_name " + "FROM quiz_result qr "
				+ "LEFT JOIN question_categories qc " + "ON qr.category_id = qc.category_id " + "WHERE quiz_id = ?";

		QuizResult quizResults = jdbcTemplate.queryForObject(query, new QuizResultRowMapper(), quizId);
		return quizResults;
	}

	@Override
	public void setQuizResultNextPk() {
		String tableName = "quiz_result";
		int quizResultNextPk = this.getQuizResultNextPk();
		String query = "UPDATE sequence_table SET next_pk_value = ? WHERE table_name = ?";
		jdbcTemplate.update(query, quizResultNextPk + 1, tableName);
	}

	@Override
	public int getQuizResultNextPk() {
		Integer nextPkValue = null;
		String tableName = "quiz_result";
		String query = "SELECT next_pk_value FROM sequence_table WHERE table_name = ?";
		try {
			nextPkValue = jdbcTemplate.queryForObject(query, (rs, rowNum) -> rs.getInt(1), tableName);
		} catch (EmptyResultDataAccessException e) {
			String insertQuery = "INSERT INTO sequence_table VALUES (?, ?)";
			jdbcTemplate.update(insertQuery, tableName, 1);
			nextPkValue = 1;
		}

		return nextPkValue;
	}

}
