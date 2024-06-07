package com.instagram.instagrambe.dto.story;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryResponseDto {
    private String storyId;
    private String Username;
    private String image;
    private String caption;
    private String storyDate;
    private int viewsCount;
    private String expiresDate;
}
