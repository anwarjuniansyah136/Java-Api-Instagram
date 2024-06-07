package com.instagram.instagrambe.service.like;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.instagram.instagrambe.dto.like.LikeResponseDto;
import com.instagram.instagrambe.entity.Post;
import com.instagram.instagrambe.entity.TableLikes;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.LikesRepository;
import com.instagram.instagrambe.repository.PostRepository;
import com.instagram.instagrambe.repository.UserRepository;

@Service
public class LikeServiceImpl implements LikeService{
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LikesRepository likesRepository;

    @Override
    public String plusLike(String postId) {
        Post post = postRepository.getReferenceById(postId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        
        TableLikes like = new TableLikes();
        like.setPost(post);
        like.setUser(user);
        like.setLikeDate(convertToDate(LocalDateTime.now()));

        likesRepository.save(like);
        return "Success";
    }

    private Date convertToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String minLike(String postId) {
        Post post = postRepository.getReferenceById(postId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        likesRepository.deleteByPostAndUser(post,user);
        return "Success";
    }

    @Override
    public List<LikeResponseDto> getByPost(String postId) {
        Post post = postRepository.getReferenceById(postId);
        List<TableLikes> likes = likesRepository.findAllByPost(post);
        return likes.stream().map(this :: toLikeResponseDto).collect(Collectors.toList());
    }

    private LikeResponseDto toLikeResponseDto(TableLikes likes){
        return LikeResponseDto.builder()
        .postId(likes.getPost().getPostId())
        .username(likes.getUser().getUsername())
        .build();
    }
    
}
