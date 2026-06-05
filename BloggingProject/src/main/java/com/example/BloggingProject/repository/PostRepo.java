package com.example.BloggingProject.repository;

import com.example.BloggingProject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {

}
