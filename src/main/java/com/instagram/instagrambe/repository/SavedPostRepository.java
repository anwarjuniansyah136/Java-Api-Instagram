package com.instagram.instagrambe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Post;
import com.instagram.instagrambe.entity.SavedPost;
import com.instagram.instagrambe.entity.User;

public interface SavedPostRepository  extends JpaRepository<SavedPost,String>{

    List<SavedPost> findAllByUser(User user);

    List<SavedPost> findSavedPostByUser(User user);

    void deleteByPost(Post post);
}