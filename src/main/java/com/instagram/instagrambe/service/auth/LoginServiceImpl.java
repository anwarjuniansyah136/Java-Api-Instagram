package com.instagram.instagrambe.service.auth;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.instagram.instagrambe.dto.auth.ForgotRequestDto;
import com.instagram.instagrambe.dto.auth.LoginRequestDto;
import com.instagram.instagrambe.dto.auth.LoginResponseDto;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.UserRepository;
import com.instagram.instagrambe.service.EmailService;
import com.instagram.instagrambe.utils.JwtUtil;

import jakarta.mail.MessagingException;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    EmailService emailService;

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getUsername());
        if(user != null){
            boolean isMatch = passwordEncoder.matches(dto.getPassword(), user.getPasswordHash());
            if(isMatch){
                LoginResponseDto response = new LoginResponseDto();
                response.setUsername(user.getUsername());
                response.setRole(user.getRole().getRoleName());
                response.setToken(jwtUtil.generateToken(user));
                return response;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Username or Password");
    }

    @Override
    public void sendEmailResetPasswordOtp(String email) {
        try {
            User user = userRepository.findByEmail(email);
            System.out.println(user);
            generateOtp(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    private void generateOtp(User user) throws UnsupportedEncodingException, MessagingException {
        String otp = RandomStringUtils.randomAlphanumeric(5);
        String encodeOtp = passwordEncoder.encode(otp);

        user.setOtp(encodeOtp);
        user.setOtpReqTime(new Date());
        
        userRepository.save(user);
        emailService.sendOTPEmail(user.getEmail(), otp);
    }

    @Override
    public void resetPassword(String email, ForgotRequestDto dto) {
        try {
            User user = userRepository.findByEmail(email);
            if(passwordEncoder.matches(dto.getOneTimePassword(), user.getOtp()) && user.isOtpRequired()){
                user.setOtp(null);
                user.setOtpReqTime(null);
                user.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
                userRepository.save(user);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
