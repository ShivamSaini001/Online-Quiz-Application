package com.quiz.services;

import java.util.List;

import com.quiz.dto.QuizQuestionDTO;
import com.quiz.entities.Quiz;
import com.quiz.entities.QuizResult;

public interface QuizService {
	
	Quiz generateQuiz(int categoryId, String userId);
	public QuizQuestionDTO getNextQuestion(String previousQuestionAnswer, int questionNumber, Quiz quiz);
	void updateQuizResult(Quiz quiz, int questionIndex, String previousQuestionAnswer);
	QuizQuestionDTO getQuizQuestion(Quiz quiz, int questionIndex);
	
	int saveQuizResult(QuizResult quizResult);
	List<QuizResult> getAllQuizResults();
	List<QuizResult> getAllQuizResultsByUserId(String email);
	QuizResult getQuizResultById(int id);
	int getQuizResultNextPk();
	void setQuizResultNextPk();
}
