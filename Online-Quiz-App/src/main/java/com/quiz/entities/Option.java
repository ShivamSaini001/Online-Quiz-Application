package com.quiz.entities;

public class Option {
	private int optionId = -1;
	private String optionText;
	private int questionId = -1;
	
	@Override
	public String toString() {
		return "Option [optionId=" + optionId + ", optionText=" + optionText + ", questionId=" + questionId + "]";
	}

	public Option() {
		
	}

	public Option(String optionText) {
		this.optionText = optionText;
	}
	
	public Option(String optionText, int questionId) {
		this.optionText = optionText;
		this.questionId = questionId;
	}

	public Option(int optionId, String optionText, int questionId) {
		this.optionId = optionId;
		this.optionText = optionText;
		this.questionId = questionId;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

}
