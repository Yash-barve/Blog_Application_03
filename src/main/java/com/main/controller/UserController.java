package com.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.main.dto.LoginForm;
import com.main.entity.UserEntity;
import com.main.service.UserService;
import com.main.util.ServiceMsg;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new UserEntity());
		return "signup";
	}

	@PostMapping("/signup")
	public String signupHandler(@ModelAttribute("user") UserEntity user, Model model) {
		ServiceMsg status = this.userService.register(user);
		if (status == ServiceMsg.REG_SUCCESS) {
			model.addAttribute("success", status.getMsg());
		} else
			model.addAttribute("failed", status.getMsg());
		return "signup";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginData",new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginHandler(@ModelAttribute("lognData")LoginForm loginData,Model model) {
		ServiceMsg status = this.userService.login(loginData);
		if(status!=ServiceMsg.LOGIN_SUCCESS) {
			model.addAttribute("failed",status.getMsg());
			model.addAttribute("loginData",loginData);
			return "login";
		}
		return "redirect:/myposts";
	}
	@GetMapping("/logout")
	public String logout() {
		this.session.invalidate();
		return "redirect:/";
	}

}
