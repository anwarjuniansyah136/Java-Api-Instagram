package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Notification;

public interface NotificationRepository  extends JpaRepository<Notification,String>{

    
}