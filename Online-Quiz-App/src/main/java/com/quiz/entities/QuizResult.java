package com.quiz.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class QuizResult {

	private int quizId;
	private String userId;
	private QuestionCategory category;
	private int totalQuestions;
	private int numberOfCorrectAnswer;
	private int numberOfWrongAnswer;
	private int numberOfNotAttemptQuestions;
	// Marks management
	private double markForEachQuestion;
	private double totalMarks;
	private double obtainedMarkes;
	private double scoreInPercentage;

	private LocalDate date;
	private LocalTime time;

	@Override
	public String toString() {
		return "QuizResult [quizId=" + quizId + ", userId=" + userId + ", category=" + category
				+ ", totalQuestions=" + totalQuestions + ", numberOfCorrectAnswer=" + numberOfCorrectAnswer
				+ ", numberOfWrongAnswer=" + numberOfWrongAnswer + ", numberOfNotAttemptQuestions="
				+ numberOfNotAttemptQuestions + ", scoreInPercentage=" + scoreInPercentage + ", date=" + date
				+ ", time=" + time + "]";
	}

	public static double calculatePersentage(double totalMarks, double obtainedMarkes) {
		return (obtainedMarkes * 100) / totalMarks;
	}

	public double getMarkForEachQuestion() {
		return markForEachQuestion;
	}

	public void setMarkForEachQuestion(double markForEachQuestion) {
		this.markForEachQuestion = markForEachQuestion;
	}

	public double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(double totalMarks) {
		this.totalMarks = totalMarks;
	}

	public double getObtainedMarkes() {
		return obtainedMarkes;
	}

	public void setObtainedMarkes(double obtainedMarkes) {
		this.obtainedMarkes = obtainedMarkes;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public QuestionCategory getCategory() {
		return category;
	}

	public void setCategory(QuestionCategory category) {
		this.category = category;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public int getNumberOfCorrectAnswer() {
		return numberOfCorrectAnswer;
	}

	public void setNumberOfCorrectAnswer(int numberOfCorrectAnswer) {
		this.numberOfCorrectAnswer = numberOfCorrectAnswer;
	}

	public int getNumberOfWrongAnswer() {
		return numberOfWrongAnswer;
	}

	public void setNumberOfWrongAnswer(int numberOfWrongAnswer) {
		this.numberOfWrongAnswer = numberOfWrongAnswer;
	}

	public int getNumberOfNotAttemptQuestions() {
		return numberOfNotAttemptQuestions;
	}

	public void setNumberOfNotAttemptQuestions(int numberOfNotAttemptQuestions) {
		this.numberOfNotAttemptQuestions = numberOfNotAttemptQuestions;
	}

	public double getScoreInPercentage() {
		return scoreInPercentage;
	}

	public void setScoreInPercentage(double scoreInPercentage) {
		this.scoreInPercentage = scoreInPercentage;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
}
