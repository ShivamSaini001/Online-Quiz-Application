package com.quiz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quiz.entities.QuestionCategory;
import com.quiz.services.QuestionService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/add")
	public String addQuesCategory(@RequestParam("categoryName") String categoryName, RedirectAttributes redirectAttributes) {
		questionService.addQuestionCategory(categoryName);
		
		redirectAttributes.addFlashAttribute("responseMessage", "Category Successfully Added...");
		return "redirect:/admin/ManageQuestions";
	}

	@PostMapping("/deleteById")
	public String deleteQuesCategoryById(@RequestParam("categoryId") int id, RedirectAttributes redirectAttributes) {

		questionService.deleteQuestionCategoryById(id);
		
		redirectAttributes.addFlashAttribute("responseMessage", "Category Successfully Deleted...");
		return "redirect:/admin/ManageQuestions";
	}

	@PostMapping("/update")
	public String updateQuesCategory(QuestionCategory questionCategory, RedirectAttributes redirectAttributes) {

		questionService.updateQuestionCategory(questionCategory);
		
		redirectAttributes.addFlashAttribute("responseMessage", "Category Successfully Updated...");

		return "redirect:/admin/ManageQuestions";
	}

	@ResponseBody
	@GetMapping(path = "/getAll")
	public List<QuestionCategory> getAllQuesCategory() {
		return questionService.getAllQuestionCategories();
	}

	@ResponseBody
	@PostMapping("/getById")
	public QuestionCategory getQuesCategoryById(@RequestParam("id") int id) {
		return questionService.getQuestionCategoryById(id);
	}

}
