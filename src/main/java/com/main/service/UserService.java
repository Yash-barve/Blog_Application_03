package com.main.service;

import com.main.dto.LoginForm;
import com.main.entity.UserEntity;
import com.main.util.ServiceMsg;

public interface UserService {
	public ServiceMsg register(UserEntity user);

	public ServiceMsg login(LoginForm loginData);
	
	public boolean checkUser();
	
	public void logout();

}
