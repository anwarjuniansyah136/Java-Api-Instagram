package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.StoryLocation;

public interface StoryLocationRepository  extends JpaRepository<StoryLocation,String>{

    
}