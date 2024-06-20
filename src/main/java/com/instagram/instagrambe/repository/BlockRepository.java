package com.instagram.instagrambe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Block;
import com.instagram.instagrambe.entity.User;

public interface BlockRepository extends JpaRepository<Block,String>{

    List<Block> findByUser(User user);

    List<Block> findByBlocker(User user);

    
}