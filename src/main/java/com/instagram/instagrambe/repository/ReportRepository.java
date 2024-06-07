package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Report;

public interface ReportRepository  extends JpaRepository<Report,String>{

    
}