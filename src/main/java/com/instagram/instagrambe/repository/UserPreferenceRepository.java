package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.UserPreference;

public interface UserPreferenceRepository extends JpaRepository<UserPreference,String>{

    
}