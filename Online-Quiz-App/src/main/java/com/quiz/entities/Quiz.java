package com.quiz.entities;

import java.util.List;

import com.quiz.dto.QuestionDTO;

public class Quiz {

	private QuizResult quizResult;

	private List<QuestionDTO> quizQuestions;
	
	@Override
	public String toString() {
		return "Quiz [quizResult=" + quizResult + ", quizQuestions=" + quizQuestions + "]";
	}

	public QuizResult getQuizResult() {
		return quizResult;
	}

	public void setQuizResult(QuizResult quizResult) {
		this.quizResult = quizResult;
	}

	public List<QuestionDTO> getQuizQuestions() {
		return quizQuestions;
	}

	public void setQuizQuestions(List<QuestionDTO> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}

}
