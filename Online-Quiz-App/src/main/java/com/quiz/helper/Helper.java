package com.quiz.helper;

import java.util.Arrays;
import java.util.List;

import com.quiz.dto.QuestionDTO;
import com.quiz.entities.Option;
import com.quiz.entities.Question;
import com.quiz.entities.QuestionCategory;

public class Helper {

	public static Question getQuestionFromQuestionDTO(QuestionDTO questionDto) {

		String correctOption = questionDto.getCorrectOption().trim();
		int categoryId = questionDto.getQuestionCategoryId();

		String optionA = questionDto.getOptionA().trim();
		String optionB = questionDto.getOptionB().trim();
		String optionC = questionDto.getOptionC().trim();
		String optionD = questionDto.getOptionD().trim();

		List<Option> allOptions = Arrays.asList(optionA, optionB, optionC, optionD).stream()
				.map(option -> new Option(option)).toList();

		Question question = new Question();

		question.setCorrectOption(new Option(correctOption));
		question.setOptions(allOptions);
		question.setQuestionCategory(new QuestionCategory(categoryId));
		question.setQuestionId(questionDto.getQuestionId());
		question.setQuestionText(questionDto.getQuestionText().trim());

		return question;
	}

	public static QuestionDTO getQuestionDTOFromQuestion(Question question) {
		QuestionDTO questionDto = new QuestionDTO();

		List<Option> optionList = question.getOptions();

		questionDto.setOptionA(optionList.get(0).getOptionText().trim());
		questionDto.setOptionB(optionList.get(1).getOptionText().trim());
		questionDto.setOptionC(optionList.get(2).getOptionText().trim());
		questionDto.setOptionD(optionList.get(3).getOptionText().trim());

		questionDto.setCorrectOption(question.getCorrectOption().getOptionText().trim());
		
		questionDto.setQuestionCategoryId(question.getQuestionCategory().getCategoryId());
		questionDto.setQuestionId(question.getQuestionId());
		questionDto.setQuestionText(question.getQuestionText().trim());

		return questionDto;
	}

}
