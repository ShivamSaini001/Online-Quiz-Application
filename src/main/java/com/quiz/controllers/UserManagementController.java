package com.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserManagementController {

	@Autowired
	UserDetailsManager userDetailsManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String registrationForm() {
		return "register";
	}

	@PostMapping("/register")
	public String processRegistrationForm(@RequestParam("username") String email,
			@RequestParam("password") String password, HttpServletRequest request) {
		
		boolean isUserExists = userDetailsManager.userExists(email);
		if(!isUserExists) {
			UserDetails user = User.withUsername(email).password(passwordEncoder.encode(password)).roles("USER").build();
			userDetailsManager.createUser(user);
			request.getSession().setAttribute("successMessage", "Registration Successfull!");
			return "redirect:/login";
		}
		else {
			request.getSession().setAttribute("errorMessage", "User with email " + email +" Already exists!");
			return "redirect:/register";
		}
	}

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping({ "/user/logout", "/admin/logout" })
	public String logoutUser() {
		return "redirect:/logout";
	}
}
