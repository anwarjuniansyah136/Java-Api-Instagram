package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.PostLocation;

public interface PostLocationRepository  extends JpaRepository<PostLocation,String>{

    
}