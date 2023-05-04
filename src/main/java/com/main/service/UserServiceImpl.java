package com.main.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.binding.Login;
import com.main.binding.Register;
import com.main.entity.Userentity;
import com.main.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserRepo userrepo;

	@Override
	public String register(Register form) {
		
		Userentity formemail = userrepo.findByEmail(form.getEmail());
		
		if(formemail != null) {
			return "false";
		}
		
		Userentity entity = new Userentity();
		BeanUtils.copyProperties(form, entity);
		
		userrepo.save(entity);
		return "success";
	}

	@Override
	public String login(Login form) {
		
		Userentity entity = userrepo.findByemailAndPassword(form.getEmail(), form.getPassword());
		
		if(entity == null) {
			return "Invalid Details Enter Correct details";
		}
		
		session.setAttribute("userid", entity.getUserid());
		
		return "success";
	}

}
