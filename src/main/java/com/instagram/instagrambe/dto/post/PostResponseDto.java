package com.instagram.instagrambe.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private String imgUrl;
    private String videoUrl;
    private String postDate;
    private int likeCount;
    private int commentCount;
}
