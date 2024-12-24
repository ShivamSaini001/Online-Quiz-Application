package com.quiz.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;

import com.quiz.entities.QuestionCategory;
import com.quiz.entities.QuizResult;

public class QuizResultRowMapper implements RowMapper<QuizResult> {

	@Override
	public QuizResult mapRow(ResultSet rs, int rowNum) throws SQLException {

		QuizResult quizResult = new QuizResult();
		quizResult.setQuizId(rs.getInt(1));
		quizResult.setUserId(rs.getString(2));
		quizResult.setTotalQuestions(rs.getInt(3));
		quizResult.setNumberOfCorrectAnswer(rs.getInt(4));
		quizResult.setNumberOfWrongAnswer(rs.getInt(5));
		quizResult.setNumberOfNotAttemptQuestions(rs.getInt(6));
		quizResult.setMarkForEachQuestion(rs.getDouble(7));
		quizResult.setTotalMarks(rs.getDouble(8));
		quizResult.setObtainedMarkes(rs.getDouble(9));
		quizResult.setScoreInPercentage(rs.getDouble(10));
		quizResult.setDate(LocalDate.parse(rs.getString(11)));
		quizResult.setTime(LocalTime.parse(rs.getString(12)));

		QuestionCategory questionCategory = new QuestionCategory();
		questionCategory.setCategoryId(rs.getInt(13));
		questionCategory.setCategoryName(rs.getString(14));
		
		quizResult.setCategory(questionCategory);
		return quizResult;
	}

}
