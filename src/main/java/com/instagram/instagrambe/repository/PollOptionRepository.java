package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.PollOption;

public interface PollOptionRepository  extends JpaRepository<PollOption,String>{

    
}