package com.instagram.instagrambe.controller.explore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagrambe.dto.GenericResponse;
import com.instagram.instagrambe.service.explore.ExploreService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/explore")
@Tag(name = "EXPLORE")
public class ExploreController {
    @Autowired
    ExploreService exploreService;

    @GetMapping("/get")
    public ResponseEntity<Object> findAll(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(exploreService.getAll(), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Object> findById(@RequestParam String id){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(exploreService.getById(id), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }
}
