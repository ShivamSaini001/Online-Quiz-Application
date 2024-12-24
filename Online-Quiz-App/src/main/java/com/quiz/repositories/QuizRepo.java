package com.quiz.repositories;

import java.util.List;

import com.quiz.entities.QuizResult;

public interface QuizRepo {
	
	int saveQuizResult(QuizResult quizResult);
	List<QuizResult> getAllQuizResults();
	List<QuizResult> getAllQuizResultsByUserId(String email);
	QuizResult getQuizResultById(int id);
	int getQuizResultNextPk();
	void setQuizResultNextPk();
}
