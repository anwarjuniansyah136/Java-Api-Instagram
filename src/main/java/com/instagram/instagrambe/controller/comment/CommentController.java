package com.instagram.instagrambe.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagrambe.dto.GenericResponse;
import com.instagram.instagrambe.service.comment.CommentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/comment")
@Tag(name = "COMMENT")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/add")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> addComment(@RequestParam String postId,@RequestParam String commentText){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(commentService.addComment(postId, commentText), "Comment Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @PutMapping("/edit")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> editComment(@RequestParam String commentId,@RequestParam String commentText){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(commentService.editComment(commentId, commentText), "Edit Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> deleteComment(@RequestParam String commentId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(commentService.deleteComment(commentId), "Delete Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getCommentByPost(@RequestParam String postId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(commentService.getCommentByPost(postId), "Fetch Data Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }
}
