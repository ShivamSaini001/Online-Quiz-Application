package com.quiz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.dto.QuestionDTO;
import com.quiz.entities.QuestionCategory;
import com.quiz.services.QuestionService;

@Controller
@RequestMapping("/admin")
public class AdminResponsibilityController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/dashboard")
	public String adminDashboard() {
		return "admin/dashboard"; // prefix: /WEB-INF/templates/ suffix: .jsp
	}

	@GetMapping("/ManageQuestions")
	public String manageQuestions(Model model) {

		List<QuestionCategory> questionCategoryList = questionService.getAllQuestionCategories();
		List<QuestionDTO> questionList = questionService.getAllQuestion();

		model.addAttribute("categoryList", questionCategoryList);
		model.addAttribute("questionList", questionList);
		System.out.println(questionCategoryList);
		System.out.println(questionList);
		return "admin/manage-questions";
	}

	@GetMapping("/ManageUsers")
	public String manageUsers() {
		return "admin/manage-users";
	}

	@GetMapping("/results")
	public String getResults() {
		return "admin/results";
	}

	@GetMapping("/settings")
	public String getSettings() {
		return "admin/settings";
	}
}
