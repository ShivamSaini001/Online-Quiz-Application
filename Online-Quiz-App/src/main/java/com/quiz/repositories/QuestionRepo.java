package com.quiz.repositories;

import java.util.List;

import com.quiz.entities.Question;
import com.quiz.entities.QuestionCategory;

public interface QuestionRepo {
	
	Question addQuestion(Question question);

	Question updateQuestion(Question question);

	boolean deleteQuestionById(int questionId);

	List<Question> getAllQuestion();

	Question getQuestionById(int id);

	List<Question> getAllQuestionByCategoryId(int categoryId);
	
	List<Question> getRandomQuestionByCategoryId(int categoryId, int size);

//	********** Question Category ************

	boolean addCategory(String categoryName);

	boolean deleteCategoryById(int id);

	void updateCategory(QuestionCategory category);

	List<QuestionCategory> getAllCategories();

	QuestionCategory getCategoryById(int id);
}
