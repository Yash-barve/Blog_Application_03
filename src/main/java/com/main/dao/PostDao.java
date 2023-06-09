package com.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.entity.BlogPost;

public interface PostDao extends JpaRepository<BlogPost, Integer> {
	@Query(value = "select p from BlogPost p where p.user.userId=:id")
	public List<BlogPost> getPostByUserId(@Param("id") Integer id);
	@Query(value="select p from BlogPost p")
	public List<BlogPost> findAllBlogPosts();
}
