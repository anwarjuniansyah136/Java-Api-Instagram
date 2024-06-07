package com.instagram.instagrambe.service.follow;

import java.util.List;

import com.instagram.instagrambe.dto.follow.FollowResponseDto;

public interface FollowService {
    public String following(String userId);
    public String delete(String followId);
    public List<FollowResponseDto> getFollower();
}
