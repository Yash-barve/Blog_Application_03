package com.main.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "POST_TBL")
public class Postentity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postid;
	
	@Lob
	private String content;
	
	private String description;
	
	private String title;
	
	@CreationTimestamp
	private LocalDate cdate;
	
	@UpdateTimestamp
	private LocalDate udate;
	
//	@OneToMany(mappedBy = "post" , cascade = CascadeType.REMOVE)
//	private List<Commententity> comments;
	
	
}
