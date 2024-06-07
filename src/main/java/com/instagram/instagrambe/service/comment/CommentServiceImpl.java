package com.instagram.instagrambe.service.comment;

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

import com.instagram.instagrambe.dto.comment.CommentResponseDto;
import com.instagram.instagrambe.entity.Comment;
import com.instagram.instagrambe.entity.Post;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.CommentRepository;
import com.instagram.instagrambe.repository.PostRepository;
import com.instagram.instagrambe.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public String addComment(String postId, String commentText) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);
        Post post = postRepository.getReferenceById(postId);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setCommentText(commentText);
        comment.setColumnDate(convertToDate(LocalDateTime.now()));
        commentRepository.save(comment);
        
        return "Comment Successfully";
    }

    private Date convertToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String editComment(String commentId, String commentText) {
        Comment comment = commentRepository.getReferenceById(commentId);
        comment.setCommentText(commentText);
        commentRepository.save(comment);
        return "Edit Comment Success";
    }

    @Override
    public String deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
        return "Delete Success";
    }

    @Override
    public List<CommentResponseDto> getCommentByPost(String postId) {
        Post post = postRepository.getReferenceById(postId);
        List<Comment> comments = commentRepository.findAllByPost(post);
        return comments.stream().map(this :: toCommentResponseDto).collect(Collectors.toList());
    }

    private CommentResponseDto toCommentResponseDto(Comment comment){
        SimpleDateFormat formatter = new SimpleDateFormat();
        String date = formatter.format(comment.getColumnDate());
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .username(comment.getUser().getUsername())
                .postId(comment.getPost().getPostId())
                .commentText(comment.getCommentText())
                .commentDate(date)
                .build();
    }
}
