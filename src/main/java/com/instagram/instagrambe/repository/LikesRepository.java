package com.instagram.instagrambe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Post;
import com.instagram.instagrambe.entity.TableLikes;
import com.instagram.instagrambe.entity.User;

public interface LikesRepository extends JpaRepository<TableLikes,String>{
    void deleteByPostAndUser(Post post, User user);

    List<TableLikes> findAllByPost(Post post);    
}