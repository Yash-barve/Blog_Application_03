package com.main.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.main.entity.BlogPost;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer commentId;
	private String name;
	private String email;
	private String content;
	@CreationTimestamp
	private LocalDate creationDate;
	@ManyToOne
	private BlogPost post;
}
