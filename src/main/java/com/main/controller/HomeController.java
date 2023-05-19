package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.main.entity.BlogPost;
import com.main.entity.Comment;
import com.main.service.PostService;

@Controller
public class HomeController {
	@Autowired
	private PostService postService;

	@GetMapping("/")
	public String home(Model model) {
		List<BlogPost> posts = this.postService.getAllPosts();
		model.addAttribute("posts",posts);
		return "home";
	}

	@GetMapping("/blog/{id}")
	public String blogPost(@PathVariable("id")Integer postId,Model model) {
		BlogPost post = this.postService.getPost(postId);
		model.addAttribute("post", post);
		model.addAttribute("madeby",post.getUser().getEmail());
		model.addAttribute("commentData",new Comment());

		return "blogpost";
	}
}
