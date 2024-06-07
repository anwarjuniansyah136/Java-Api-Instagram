package com.instagram.instagrambe.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.instagram.instagrambe.dto.GenericResponse;
import com.instagram.instagrambe.service.post.PostService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/post")
@Tag(name = "POST")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> create(@RequestParam String caption,@RequestParam(required = false) MultipartFile image,@RequestParam(required = false) MultipartFile video){
        try {
            postService.postPhotoOrVideo(caption, image, video);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Create Post Successfully"));
        }
        catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.error(e.getReason()));
        }
         catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please, Try Again"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> delete(@RequestParam String postId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(postService.delete(postId), "Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please, Try Again"));
        }
    }

    @PutMapping("/update")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> update(String postId,String caption){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(postService.update(postId, caption), "Update Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getByUser(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(postService.get(), "Successfully to fetch data"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Failed fetch data"));
        }
    }
}
