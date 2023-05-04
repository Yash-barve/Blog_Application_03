package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Userentity;

public interface UserRepo extends JpaRepository<Userentity, Integer> {

	public Userentity findByEmail(String email);
	
	public Userentity findByemailAndPassword(String email, String password);
	
}
