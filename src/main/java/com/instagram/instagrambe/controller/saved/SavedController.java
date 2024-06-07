package com.instagram.instagrambe.controller.saved;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagrambe.dto.GenericResponse;
import com.instagram.instagrambe.service.saved.SavedService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/saved")
public class SavedController {
    @Autowired
    SavedService savedService;

    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> createSave(@RequestParam String postId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(savedService.createSaved(postId), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Pelasae,Try Again"));
        }
    }
    
    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getSaved(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(savedService.getAll(), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Pelasae,Try Again"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> deleteSaved(@RequestParam String savedId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(savedService.deleteSaved(savedId), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Pelasae,Try Again"));
        }
    }
}
