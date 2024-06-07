package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.PostAnalytics;

public interface PostAnalyticsRepository  extends JpaRepository<PostAnalytics,String>{

    
}