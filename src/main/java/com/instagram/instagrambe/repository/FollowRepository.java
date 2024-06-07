package com.instagram.instagrambe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Follow;
import com.instagram.instagrambe.entity.User;

public interface FollowRepository extends JpaRepository<Follow,String>{

    List<Follow> findByFollower(User user);

    
}