package com.instagram.instagrambe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Message;
import com.instagram.instagrambe.entity.User;

public interface MessageRepository  extends JpaRepository<Message,String>{

    List<Message> findBySender(User user);

}