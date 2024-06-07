package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.PollVote;

public interface PollVoteRepository  extends JpaRepository<PollVote,String>{

    
}