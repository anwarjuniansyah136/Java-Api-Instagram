package com.instagram.instagrambe.service.comment;

import java.util.List;

import com.instagram.instagrambe.dto.comment.CommentResponseDto;

public interface CommentService {
    public String addComment(String postId,String commentText);
    public String editComment(String postId,String commentText);
    public String deleteComment(String commentId);
    public List<CommentResponseDto> getCommentByPost(String postId);
}
