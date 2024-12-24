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

import com.quiz.dto.QuestionDTO;
import com.quiz.services.QuestionService;

@Controller
@RequestMapping("/admin/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/add")
	public String addQuestion(QuestionDTO questionDTO, RedirectAttributes redirectAttributes) {
		questionService.addQuestion(questionDTO);
		
		redirectAttributes.addFlashAttribute("responseMessage", "Question Successfully Added...");
		return "redirect:/admin/ManageQuestions";
	}

	@PostMapping("/update")
	public String updateQuestion(QuestionDTO questionDTO, RedirectAttributes redirectAttributes) {
		questionService.updateQuestion(questionDTO);
		
		redirectAttributes.addFlashAttribute("responseMessage", "Question Successfully Updated...");
		return "redirect:/admin/ManageQuestions";
	}

	@PostMapping("/delete")
	public String deleteQuestion(@RequestParam("questionId") int questionId, RedirectAttributes redirectAttributes) {
		questionService.deleteQuestionById(questionId);
		
		redirectAttributes.addFlashAttribute("responseMessage", "Question Successfully Deleted...");
		return "redirect:/admin/ManageQuestions";
	}

	@ResponseBody
	@GetMapping("/getAll")
	public List<QuestionDTO> getAllQuestion() {

		return questionService.getAllQuestion();
	}

}
