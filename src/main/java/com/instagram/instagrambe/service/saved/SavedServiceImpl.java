package com.instagram.instagrambe.service.saved;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.instagram.instagrambe.dto.saved.SavedResponseDto;
import com.instagram.instagrambe.entity.Post;
import com.instagram.instagrambe.entity.SavedPost;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.PostRepository;
import com.instagram.instagrambe.repository.SavedPostRepository;
import com.instagram.instagrambe.repository.UserRepository;

@Service
public class SavedServiceImpl implements SavedService{
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SavedPostRepository savedPostRepository;

    @Override
    public String createSaved(String postId) {
        Post post = postRepository.getReferenceById(postId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        SavedPost savedPost = new SavedPost();
        savedPost.setPost(post);
        savedPost.setUser(user);
        savedPost.setSavedDate(convertToDate(LocalDateTime.now()));

        savedPostRepository.save(savedPost);
        return "Success";
    }
    
    private Date convertToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public List<SavedResponseDto> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        List<SavedPost> savedPosts = savedPostRepository.findSavedPostByUser(user);
        return savedPosts.stream().map(this :: toSavedResponseDto).collect(Collectors.toList());
    }
    private SavedResponseDto toSavedResponseDto(SavedPost savedPost){
        SimpleDateFormat formatter = new SimpleDateFormat();
        String date = formatter.format(savedPost.getSavedDate());
        return SavedResponseDto.builder()
            .savedPostId(savedPost.getSavedPostId())
            .username(savedPost.getUser().getUsername())
            .savedDate(date)
            .caption(savedPost.getPost().getCaption())
            .build();
    }

    @Override
    public String deleteSaved(String postId) {
        Post post = postRepository.getReferenceById(postId);
        savedPostRepository.deleteByPost(post);
        return "Success";
    }
}
