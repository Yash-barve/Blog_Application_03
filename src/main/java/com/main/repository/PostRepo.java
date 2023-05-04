package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Postentity;

public interface PostRepo extends JpaRepository<Postentity, Integer> {

}
