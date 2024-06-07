package com.instagram.instagrambe.service.like;

import java.util.List;

import com.instagram.instagrambe.dto.like.LikeResponseDto;

public interface LikeService {
    public String plusLike(String postId);
    public String minLike(String postId);
    public List<LikeResponseDto> getByPost(String postId);
}
