package com.instagram.instagrambe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Story;
import com.instagram.instagrambe.entity.User;

public interface StoryRepository  extends JpaRepository<Story,String>{

    List<Story> findByUser(User user);

    
}