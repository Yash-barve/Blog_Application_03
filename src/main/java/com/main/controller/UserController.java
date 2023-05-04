package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.main.binding.Register;
import com.main.binding.Login;
import com.main.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/register")
	public String Register(Model model) {
		model.addAttribute("user", new Register());
		return "register";
	}
	
	@PostMapping("/handleregister")
	public String HandleRegister(@ModelAttribute("user") Register form , Model model) {
		
		String result = userservice.register(form);
		
		if(result.contains("success")) {
			model.addAttribute("succmsg","Account Created Successfully");
			model.addAttribute("user", new Register());
		}else {
			model.addAttribute("errmsg","oops , Something Went Wrong");
		}
		
		return "register";
	}
	
	@GetMapping("/login")
	public String Login(Model model) {
		model.addAttribute("loginuser" , new Login());
		return "login";
	}
	
	@PostMapping("/handlelogin")
	public String HandlePage(@ModelAttribute("loginuser") Login login , Model model) {
		
		String result = userservice.login(login);
		
		if(result.contains("success")) {
			return "redirect:/dashboard";
		}else {
			model.addAttribute("errmsg", "Invalid Details Choose correct details");
		}
		
		return "login";
	}


}
