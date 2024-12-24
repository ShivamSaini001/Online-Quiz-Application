package com.quiz.dto;

public class QuizQuestionDTO {

	private int questionNumber = -1;
	private int totalQuestions;
	private boolean isNextQuestionAvailable;

	private String questionText;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;

	private String previousQuestionAnswer;

	public boolean isNextQuestionAvailable() {
		return isNextQuestionAvailable;
	}

	public void setNextQuestionAvailable(boolean isNextQuestionAvailable) {
		this.isNextQuestionAvailable = isNextQuestionAvailable;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getPreviousQuestionAnswer() {
		return previousQuestionAnswer;
	}

	public void setPreviousQuestionAnswer(String previousQuestionAnswer) {
		this.previousQuestionAnswer = previousQuestionAnswer;
	}

}
