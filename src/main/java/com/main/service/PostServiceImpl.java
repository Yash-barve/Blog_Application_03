package com.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.CommentDao;
import com.main.dao.PostDao;
import com.main.dto.PostDto;
import com.main.entity.BlogPost;
import com.main.entity.Comment;
import com.main.entity.UserEntity;
import com.main.service.PostService;
import com.main.util.ServiceMsg;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDao postDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private HttpSession session;

	@Override
	public List<BlogPost> getAllPosts() {
		return this.postDao.findAllBlogPosts();
	}

	@Override
	public List<BlogPost> getMyPosts() {
		UserEntity user = (UserEntity) session.getAttribute("user");
		return this.postDao.getPostByUserId(user.getUserId());
	}

	@Override
	public BlogPost getPost(Integer postId) {
		Optional<BlogPost> postOpt = this.postDao.findById(postId);
		BlogPost post = new BlogPost();
		if (postOpt.isPresent())
			post = postOpt.get();
		return post;
	}

	@Override
	public ServiceMsg addNewPost(PostDto postData) {
		if (postData.getTitle().isBlank() || postData.getDescription().isBlank() || postData.getContent().isBlank()) {
			return ServiceMsg.EMPTY_FIELD;
		}
		BlogPost post = new BlogPost();
		BeanUtils.copyProperties(postData, post);
		UserEntity u = (UserEntity) this.session.getAttribute("user");
		post.setUser(u);
		this.postDao.save(post);
		return ServiceMsg.POST_ADDED;
	}

	@Override
	public ServiceMsg updatePost(Integer id, PostDto postData) {
		BlogPost post = new BlogPost();
		Optional<BlogPost> postOpt = this.postDao.findById(id);
		if (postOpt.isPresent())
			post = postOpt.get();

		post.setTitle(postData.getTitle());
		post.setDescription(postData.getDescription());
		post.setContent(postData.getContent());
		this.postDao.save(post);
		return ServiceMsg.POST_UPDATED;
	}

	@Override
	public List<Comment> getCommentsByUser() {
		UserEntity user = (UserEntity) this.session.getAttribute("user");
		List<BlogPost> posts = this.postDao.getPostByUserId(user.getUserId());
		List<Comment> comments = new ArrayList<>();
		for (BlogPost post : posts) {
			comments.addAll(post.getComments());
		}
		return comments;
	}

	@Override
	public ServiceMsg addComment(Integer pid, Comment data) {
		BlogPost post = new BlogPost();
		Optional<BlogPost> postOpt = this.postDao.findById(pid);
		if(postOpt.isPresent()) {
			post = postOpt.get();
		}
		data.setPost(post);
		this.commentDao.save(data);
		return ServiceMsg.COMMENTED;
	}

	@Override
	public ServiceMsg deletePost(Integer id) {
		this.postDao.deleteById(id);
		return ServiceMsg.POST_DELETED;
	}

	@Override
	public void deleteComment(Integer commentId) {
		this.commentDao.deleteById(commentId);
	}

}
