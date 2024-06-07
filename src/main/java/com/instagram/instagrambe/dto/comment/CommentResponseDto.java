package com.instagram.instagrambe.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {
    private String commentId;
    private String username;
    private String postId;
    private String commentText;
    private String commentDate;
}
