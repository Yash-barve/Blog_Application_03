package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Commententity;

public interface CommentRepo extends JpaRepository<Commententity, Integer> {

}
