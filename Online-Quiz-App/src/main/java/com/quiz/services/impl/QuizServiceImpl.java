package com.quiz.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.dto.QuestionDTO;
import com.quiz.dto.QuizQuestionDTO;
import com.quiz.entities.Quiz;
import com.quiz.entities.QuizResult;
import com.quiz.repositories.QuizRepo;
import com.quiz.services.QuestionService;
import com.quiz.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuestionService questionService;

	@Autowired
	QuizRepo quizRepo;

	public Quiz generateQuiz(int categoryId, String userId) {

		int size = 5;
		double markForEachQuestion = 1.5;

		QuizResult quizResult = new QuizResult();
		quizResult.setUserId(userId);
		quizResult.setCategory(questionService.getQuestionCategoryById(categoryId));
		quizResult.setNumberOfCorrectAnswer(0);
		quizResult.setNumberOfNotAttemptQuestions(0);
		quizResult.setNumberOfWrongAnswer(0);
		quizResult.setScoreInPercentage(0);
		quizResult.setTime(LocalTime.now());
		quizResult.setDate(LocalDate.now());
		quizResult.setMarkForEachQuestion(markForEachQuestion);

		Quiz quiz = new Quiz();
		quiz.setQuizResult(quizResult);
		// Fetch Random Questions from Database
		List<QuestionDTO> randomQuestionDto = questionService.getRandomQuestionByCategoryId(categoryId, size);

		quizResult.setTotalQuestions(randomQuestionDto.size());
		quiz.setQuizQuestions(randomQuestionDto);

		return quiz;
	}

	public QuizQuestionDTO getNextQuestion(String previousQuestionAnswer, int questionNumber, Quiz quiz) {

		QuizQuestionDTO nextQuestion = new QuizQuestionDTO();
		int totalQuestions = quiz.getQuizResult().getTotalQuestions();

		if (quiz != null) {
			List<QuestionDTO> quizQuestions = quiz.getQuizQuestions();

			// if Request comes for first question
			if (questionNumber == 1) {
				nextQuestion = this.getQuizQuestion(quiz, questionNumber - 1);
			}
			// if user request remain questions
			else if ((questionNumber > 1) && (questionNumber <= totalQuestions + 1)) {

				this.updateQuizResult(quiz, questionNumber - 1, previousQuestionAnswer);
				if (questionNumber <= totalQuestions) {
					nextQuestion = this.getQuizQuestion(quiz, questionNumber - 1);
				}
				// setting answer of last question.
				nextQuestion.setPreviousQuestionAnswer(quizQuestions.get(questionNumber - 2).getCorrectOption());
			}
		}
		return nextQuestion;
	}

	public void updateQuizResult(Quiz quiz, int questionIndex, String previousQuestionAnswer) {
		QuizResult quizResult = quiz.getQuizResult();

		// If Question is not attempt
		if (previousQuestionAnswer.isEmpty() || previousQuestionAnswer.isBlank()) {
			quizResult.setNumberOfNotAttemptQuestions(quizResult.getNumberOfNotAttemptQuestions() + 1);
		}
		// If Answer is Correct
		else if (quiz.getQuizQuestions().get(questionIndex - 1).getCorrectOption().equals(previousQuestionAnswer)) {
			quizResult.setNumberOfCorrectAnswer(quizResult.getNumberOfCorrectAnswer() + 1);
		}
		// If Answer is Wrong
		else {
			quizResult.setNumberOfWrongAnswer(quizResult.getNumberOfWrongAnswer() + 1);
		}
	}

	public QuizQuestionDTO getQuizQuestion(Quiz quiz, int questionIndex) {
		List<QuestionDTO> quizQuestions = quiz.getQuizQuestions();

		QuestionDTO questionDTO = quizQuestions.get(questionIndex);
		QuizQuestionDTO quizQuestion = new QuizQuestionDTO();
		quizQuestion.setTotalQuestions(quiz.getQuizResult().getTotalQuestions());
		quizQuestion.setQuestionNumber(questionIndex + 1);
		quizQuestion.setQuestionText(questionDTO.getQuestionText());
		quizQuestion.setOptionA(questionDTO.getOptionA());
		quizQuestion.setOptionB(questionDTO.getOptionB());
		quizQuestion.setOptionC(questionDTO.getOptionC());
		quizQuestion.setOptionD(questionDTO.getOptionD());

		if (questionIndex > 0) {
			quizQuestion.setPreviousQuestionAnswer(quizQuestions.get(questionIndex - 1).getCorrectOption());
		}
		if ((questionIndex + 1) < quiz.getQuizResult().getTotalQuestions()) {
			quizQuestion.setNextQuestionAvailable(true);
		} else {
			quizQuestion.setNextQuestionAvailable(false);
		}
		return quizQuestion;
	}

	@Override
	public int saveQuizResult(QuizResult quizResult) {
		return quizRepo.saveQuizResult(quizResult);
	}
	

	@Override
	public List<QuizResult> getAllQuizResults() {
		return quizRepo.getAllQuizResults();
	}
	

	@Override
	public List<QuizResult> getAllQuizResultsByUserId(String email) {
		return quizRepo.getAllQuizResultsByUserId(email);
	}
	

	@Override
	public QuizResult getQuizResultById(int id) {
		return quizRepo.getQuizResultById(id);
	}
	

	@Override
	public int getQuizResultNextPk() {
		return quizRepo.getQuizResultNextPk();
	}
	

	@Override
	public void setQuizResultNextPk() {
		quizRepo.setQuizResultNextPk();
	}

}
