package com.quiz.services;

import java.util.List;

import com.quiz.dto.QuestionDTO;
import com.quiz.entities.Question;
import com.quiz.entities.QuestionCategory;

public interface QuestionService {
	void addQuestion(QuestionDTO questionDto);
	
	void updateQuestion(QuestionDTO questionDto);
	
	List<QuestionDTO> getAllQuestion();
	
	boolean deleteQuestionById(int questionId);
	
	Question getQuestionById(int id);

	List<Question> getAllQuestionByCategoryId(int categoryId);
	
	List<QuestionDTO> getRandomQuestionByCategoryId(int categoryId, int size);
	
//	********** Question Category ************

	boolean addQuestionCategory(String CategoryName);

	boolean deleteQuestionCategoryById(int id);

	void updateQuestionCategory(QuestionCategory questionCategory);

	List<QuestionCategory> getAllQuestionCategories();

	QuestionCategory getQuestionCategoryById(int id);
}
