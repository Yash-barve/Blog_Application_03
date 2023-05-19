package com.main.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.dao.UserDao;
import com.main.dto.LoginForm;
import com.main.entity.UserEntity;
import com.main.util.ServiceMsg;



@Service
public class UserServiceImpl implements UserService {
	
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private HttpSession session;
	

	public ServiceMsg register(UserEntity user) {
		if (user.getFname().isBlank() || user.getLname().isBlank() || user.getEmail().isBlank()
				|| user.getPwd().isBlank()) {
			return ServiceMsg.EMPTY_FIELD;
		}
		UserEntity u = this.userDao.findByEmail(user.getEmail());
		if (u != null)
			return ServiceMsg.ACCOUNT_EXISTS;
		
		user.setPwd((user.getPwd()));
		this.userDao.save(user);
		
		return ServiceMsg.REG_SUCCESS;
	}

	@Override
	public ServiceMsg login(LoginForm loginData) {
		if (loginData.getEmail().isBlank() || loginData.getPwd().isBlank()) {
			return ServiceMsg.EMPTY_FIELD;
		}
		
		UserEntity user = this.userDao.findByEmail(loginData.getEmail());
		
		if (user == null)
			return ServiceMsg.NOUSER_FOUND;
		
		this.session.setAttribute("user", user);
		return ServiceMsg.LOGIN_SUCCESS;
	}

	@Override
	public boolean checkUser() {
		return session.getAttribute("user") != null;
	}

	@Override
	public void logout() {
		this.session.invalidate();
	}

}
