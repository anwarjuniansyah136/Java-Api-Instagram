package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.PostView;

public interface PostViewRepository  extends JpaRepository<PostView,String>{

    
}