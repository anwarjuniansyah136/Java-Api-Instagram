package com.instagram.instagrambe.controller.message;

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
import com.instagram.instagrambe.service.message.MessageService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/message")
@Tag(name = "MESSAGE")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> create(@RequestParam String userId,@RequestParam String text){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(messageService.create(userId, text), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }
    
    @PutMapping("/edit")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> edit(@RequestParam String messageId,String text){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(messageService.edit(messageId, text), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> delete(@RequestParam String messageId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(messageService.delete(messageId), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }

    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> get(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(messageService.get(), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("Please,Try Again"));
        }
    }
}
