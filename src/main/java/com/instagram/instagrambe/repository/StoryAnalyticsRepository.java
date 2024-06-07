package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.StoryAnalytics;

public interface StoryAnalyticsRepository  extends JpaRepository<StoryAnalytics,String>{

    
}