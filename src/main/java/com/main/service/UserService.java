package com.main.service;

import com.main.binding.Login;
import com.main.binding.Register;

public interface UserService {

	public String register(Register form);
	
	public String login(Login form);
	
}
