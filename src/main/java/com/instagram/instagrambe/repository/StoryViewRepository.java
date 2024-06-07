package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.StoryView;

public interface StoryViewRepository  extends JpaRepository<StoryView,String>{

    
}