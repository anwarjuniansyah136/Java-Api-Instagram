package com.instagram.instagrambe.dto.follow;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowResponseDto {
    private String followId;
    private String follower;
    private String following;
    private String date;
}
