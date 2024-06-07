package com.instagram.instagrambe.service.follow;

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

import com.instagram.instagrambe.dto.follow.FollowResponseDto;
import com.instagram.instagrambe.entity.Follow;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.FollowRepository;
import com.instagram.instagrambe.repository.UserRepository;

@Service
public class FollowServiceImpl implements FollowService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

    @Override
    public String following(String userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User following = userRepository.findByUsername(username);
        User follower = userRepository.getReferenceById(userId);
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        follow.setFollowDate(convertToDate(LocalDateTime.now()));
        followRepository.save(follow);
        return "success";
    }

    private Date convertToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String delete(String followId) {
        Follow follow = followRepository.getReferenceById(followId);
        followRepository.delete(follow);
        return "Success";
    }

    @Override
    public List<FollowResponseDto> getFollower() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        List<Follow> follows = followRepository.findByFollower(user);
        return follows.stream().map(this :: toFollowResponseDto).collect(Collectors.toList());
    }

    private FollowResponseDto toFollowResponseDto(Follow follow){
        return FollowResponseDto.builder()
            .followId(follow.getFollowId())
            .follower(follow.getFollower().getUsername())
            .following(follow.getFollowing().getUsername())
            .date(toString(follow.getFollowDate()))
            .build();
    }

    private String toString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat();
        String dateToString = formatter.format(date);
        return dateToString;
    }
    
}
