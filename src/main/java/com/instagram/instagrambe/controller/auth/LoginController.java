package com.instagram.instagrambe.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagrambe.dto.GenericResponse;
import com.instagram.instagrambe.dto.auth.ForgotRequestDto;
import com.instagram.instagrambe.dto.auth.LoginRequestDto;
import com.instagram.instagrambe.service.auth.LoginService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")

    public ResponseEntity<Object> login(@RequestBody LoginRequestDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(loginService.login(dto), "Login Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/forgot-password")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> forgotPassword(@RequestParam String email){
        try {
            loginService.sendEmailResetPasswordOtp(email);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Successfully Send OTP to email"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/reset-password")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> resetPassword(@RequestParam String email,@RequestBody ForgotRequestDto dto){
        try {
            loginService.resetPassword(email, dto);
            return ResponseEntity.ok().body(GenericResponse.success(null, "Successfully Change Password"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error(e.getMessage()));
        }
    }
}
