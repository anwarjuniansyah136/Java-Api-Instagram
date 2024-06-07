package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Poll;

public interface PollRepository  extends JpaRepository<Poll,String>{

    
}