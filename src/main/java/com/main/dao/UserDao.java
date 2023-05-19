package com.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByEmail(String email);
}
