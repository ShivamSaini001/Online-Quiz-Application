package com.quiz.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quiz.dto.QuizQuestionDTO;
import com.quiz.entities.Quiz;
import com.quiz.entities.QuizResult;
import com.quiz.services.QuestionService;
import com.quiz.services.QuizService;

@Controller
@RequestMapping("/user/quiz")
public class QuizController {

	private Map<String, Quiz> userQuiz;
	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	public QuizController() {
		this.userQuiz = new LinkedHashMap<String, Quiz>();
	}

	@PostMapping("/create")
	public String quizSetup(@RequestParam("questionCategoryId") int categoryId, @RequestParam("email") String email,
			RedirectAttributes redirectAttributes, Model model) {

		Quiz quiz = quizService.generateQuiz(categoryId, email);
		String categoryName = questionService.getQuestionCategoryById(categoryId).getCategoryName();

		// If Quiz Does not have any Questions
		if (quiz.getQuizQuestions().size() == 0) {
			redirectAttributes.addFlashAttribute("noQuestionFromCategory", "No questions found from Category: ");
			redirectAttributes.addFlashAttribute("categoryNameNotFoundQuestions", categoryName);
			return "redirect:/user/home";
		}
		// If Quiz have Question
		userQuiz.put(email, quiz);
		model.addAttribute("quizDetails", quiz.getQuizResult());

		return "users/play-quiz";
	}

	@ResponseBody
	@PostMapping("/getNextQuestion")
	public QuizQuestionDTO getNextQuestion(@RequestParam("questionAnswer") String previousQuestionAnswer,
			@RequestParam("questionNumber") int questionNumber, Authentication authentication) {

		String email = authentication.getName();
		Quiz quiz = userQuiz.get(email);
		QuizQuestionDTO nextQuestion = quizService.getNextQuestion(previousQuestionAnswer, questionNumber, quiz);
		return nextQuestion;
	}

	@GetMapping("/result/{quizId}")
	public String seeQuizResult(@PathVariable("quizId") int quizId, Model model) {
		// Fetch Quiz from Database using quizId.
		 QuizResult quizResult = quizService.getQuizResultById(quizId);
		 model.addAttribute("quizResult", quizResult);
		 
		return "users/quiz-results";
	}

	@GetMapping("/saveResult")
	public String saveQuizResult(Authentication authentication) {
		String email = authentication.getName();
		Quiz quiz = userQuiz.get(email);
		QuizResult quizResult = quiz.getQuizResult();
		quizResult.setQuizId(quizService.getQuizResultNextPk());
		quizService.setQuizResultNextPk();

		int totalQuetions = quizResult.getTotalQuestions();
		int correctAnswers = quizResult.getNumberOfCorrectAnswer();
		double markForEachQuestion = quizResult.getMarkForEachQuestion();

		// Calculating Marks and Percentage
		quizResult.setTotalMarks(totalQuetions * markForEachQuestion);
		quizResult.setObtainedMarkes(correctAnswers * markForEachQuestion);
		double resultInPercentage = QuizResult.calculatePersentage(quizResult.getTotalMarks(),
				quizResult.getObtainedMarkes());
		quizResult.setScoreInPercentage(resultInPercentage);

		System.out.println("***************** Quiz Result ***********************");
		quizService.saveQuizResult(quizResult);

		return "redirect:/user/quiz/result/" + quizResult.getQuizId();
	}

}
