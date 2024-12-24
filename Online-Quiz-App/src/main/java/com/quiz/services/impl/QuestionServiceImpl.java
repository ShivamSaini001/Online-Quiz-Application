package com.quiz.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.dto.QuestionDTO;
import com.quiz.entities.Question;
import com.quiz.entities.QuestionCategory;
import com.quiz.helper.Helper;
import com.quiz.repositories.QuestionRepo;
import com.quiz.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepo questionRepo;

	@Override
	public void addQuestion(QuestionDTO questionDto) {

		// Validation Code

		Question question = Helper.getQuestionFromQuestionDTO(questionDto);

		questionRepo.addQuestion(question);
	}

	@Override
	public void updateQuestion(QuestionDTO questionDto) {

		// Validation Code

		Question question = Helper.getQuestionFromQuestionDTO(questionDto);

		questionRepo.updateQuestion(question);
	}

	@Override
	public boolean deleteQuestionById(int questionId) {

		return questionRepo.deleteQuestionById(questionId);
	}

	@Override
	public List<QuestionDTO> getAllQuestion() {

		List<Question> allQuestion = questionRepo.getAllQuestion();

		List<QuestionDTO> questionDtoList = allQuestion.stream()
				.map(question -> Helper.getQuestionDTOFromQuestion(question)).toList();

		return questionDtoList;
	}

	@Override
	public Question getQuestionById(int questionId) {

		return questionRepo.getQuestionById(questionId);
	}

	@Override
	public List<Question> getAllQuestionByCategoryId(int categoryId) {

		return questionRepo.getAllQuestionByCategoryId(categoryId);
	}

	@Override
	public List<QuestionDTO> getRandomQuestionByCategoryId(int categoryId, int size) {
		
		List<Question> randomQuestions = questionRepo.getRandomQuestionByCategoryId(categoryId, size);

		List<QuestionDTO> randomQuestionsDto = randomQuestions.stream()
				.map(question -> Helper.getQuestionDTOFromQuestion(question)).toList();
		
		return randomQuestionsDto;
	}

//	********** Question Category ************
	@Override
	public boolean addQuestionCategory(String CategoryName) {
		if (CategoryName == null || CategoryName.isBlank())
			throw new RuntimeException("Category name cannot be persist because it is empty.");

		return questionRepo.addCategory(CategoryName.trim());
	}

	@Override
	public boolean deleteQuestionCategoryById(int id) {
		if (id <= 0)
			throw new RuntimeException("Cannot delete Question Category because Invalid category id.");

		return questionRepo.deleteCategoryById(id);
	}

	@Override
	public void updateQuestionCategory(QuestionCategory questionCategory) {

		int id = questionCategory.getCategoryId();
		String name = questionCategory.getCategoryName().trim();

		if (id <= 0 || name == null || name.isBlank())
			throw new RuntimeException("Cannot Update Question Category because Invalid category details.");

		questionRepo.updateCategory(questionCategory);
	}

	@Override
	public List<QuestionCategory> getAllQuestionCategories() {
		return questionRepo.getAllCategories();
	}

	@Override
	public QuestionCategory getQuestionCategoryById(int id) {
		if (id <= 0)
			throw new RuntimeException("Cannot Fetch Question Category details because Invalid category id.");

		return questionRepo.getCategoryById(id);
	}

}
