package com.quiz.entities;

import java.util.List;

public class Question {

	private int questionId = -1;
	private String questionText;
	private Option correctOption;
	private List<Option> options;
	private QuestionCategory questionCategory;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Option getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(Option correctOption) {
		this.correctOption = correctOption;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionText=" + questionText + ", correctOption="
				+ correctOption + ", options=" + options + ", questionCategory=" + questionCategory + "]";
	}

}
