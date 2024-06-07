package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Tag;

public interface TagRepository  extends JpaRepository<Tag,String>{

    
}