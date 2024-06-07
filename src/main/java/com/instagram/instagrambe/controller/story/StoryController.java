package com.instagram.instagrambe.controller.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.instagram.instagrambe.dto.GenericResponse;
import com.instagram.instagrambe.service.story.StoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/story")
public class StoryController {
    @Autowired
    StoryService storyService;

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> createStory(@RequestParam String caption,@RequestParam("image") MultipartFile file){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(storyService.createStory(caption,file),"Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> deleteStory(@RequestParam String storyId){
        try{
            return ResponseEntity.ok().body(GenericResponse.success(storyService.deleteStory(storyId),"Success"));
        } catch(Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @GetMapping("/get-by-user")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getByUser(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(storyService.getStoryByUser(), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }
}
