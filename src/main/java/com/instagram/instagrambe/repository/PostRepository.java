package com.instagram.instagrambe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Post;
import com.instagram.instagrambe.entity.User;

public interface PostRepository extends JpaRepository<Post,String>{
    List<Post> findAllByUser(User user);
    
}