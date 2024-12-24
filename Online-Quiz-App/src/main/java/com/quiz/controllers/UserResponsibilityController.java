package com.quiz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.entities.QuestionCategory;
import com.quiz.entities.QuizResult;
import com.quiz.services.QuestionService;
import com.quiz.services.QuizService;

@Controller
@RequestMapping("user")
public class UserResponsibilityController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuizService quizService;

	@GetMapping({"/home"})
	public String homePage(Authentication authentication ,Model model) {
		List<QuestionCategory> questionCategoryList = questionService.getAllQuestionCategories();
		String email = authentication.getName();
		model.addAttribute("categoryList", questionCategoryList);
		model.addAttribute("email", email);
		
		return "users/user-home-page";
	}
	
	@GetMapping({"/quiz/result/home"})
	public String goToHomePage() {
		return "redirect:/user/home";
	}
	
	@GetMapping("/quizHistory")
	public String quizHistory(Authentication authentication ,Model model) {
		String email = authentication.getName();
		List<QuizResult> allQuizResults = quizService.getAllQuizResultsByUserId(email);
		model.addAttribute("allQuizResults", allQuizResults);
		return "users/quiz-history";
	}

}
