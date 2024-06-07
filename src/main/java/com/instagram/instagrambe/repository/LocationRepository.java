package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Location;

public interface LocationRepository extends JpaRepository<Location,String>{

    
}