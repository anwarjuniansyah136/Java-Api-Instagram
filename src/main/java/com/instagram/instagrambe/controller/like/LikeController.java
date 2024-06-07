package com.instagram.instagrambe.controller.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagrambe.dto.GenericResponse;
import com.instagram.instagrambe.service.like.LikeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/like")
@Tag(name = "LIKE")
public class LikeController {
    @Autowired
    LikeService likeService;

    @PostMapping("/plus")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> addLike(@RequestParam String postId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(likeService.plusLike(postId), "Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @DeleteMapping("/min")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> minLike(@RequestParam String postId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(likeService.minLike(postId), "Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getLike(@RequestParam String postId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(likeService.getByPost(postId), "Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }
}
