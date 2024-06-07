package com.instagram.instagrambe.controller.registrasi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagrambe.dto.GenericResponse;
import com.instagram.instagrambe.dto.registrasi.UserRegistrationRequestDto;
import com.instagram.instagrambe.service.registrasi.RegistrationService;

@RestController
@RequestMapping("/user")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/registration")
    public ResponseEntity<Object> register(@RequestBody UserRegistrationRequestDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(registrationService.register(dto), "Create Account Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error(e.getMessage()));
        }
    }
}
