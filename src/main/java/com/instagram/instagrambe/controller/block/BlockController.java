package com.instagram.instagrambe.controller.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagrambe.dto.GenericResponse;
import com.instagram.instagrambe.service.block.BlockService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/block")
@Tag(name = "BLOCK")
public class BlockController {
    @Autowired
    BlockService blockService;

    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> create(@RequestParam String user_id){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(blockService.create(user_id), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> delete(@RequestParam String blockId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(blockService.delete(blockId), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> get(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(blockService.get(), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }
}
