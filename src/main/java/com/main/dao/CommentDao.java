package com.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>{

}
