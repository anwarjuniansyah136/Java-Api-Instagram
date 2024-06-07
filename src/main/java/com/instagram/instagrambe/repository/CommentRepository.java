package com.instagram.instagrambe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Comment;
import com.instagram.instagrambe.entity.Post;

public interface CommentRepository extends JpaRepository<Comment,String>{
    List<Comment> findAllByPost(Post post);
    
}