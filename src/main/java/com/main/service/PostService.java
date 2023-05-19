package com.main.service;

import java.util.List;

import com.main.dto.PostDto;
import com.main.entity.BlogPost;
import com.main.entity.Comment;
import com.main.util.ServiceMsg;

public interface PostService {
	public List<BlogPost> getAllPosts();

	public List<BlogPost> getMyPosts();

	public BlogPost getPost(Integer postId);

	public ServiceMsg addNewPost(PostDto postData);
	
	public ServiceMsg updatePost(Integer id,PostDto postData);
	
	public ServiceMsg deletePost(Integer id);

	public ServiceMsg addComment(Integer pid,Comment data);
	
	public void deleteComment(Integer commentId);

	public List<Comment> getCommentsByUser();
}
