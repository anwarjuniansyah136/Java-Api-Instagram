package com.instagram.instagrambe.service.auth;

import com.instagram.instagrambe.dto.auth.ForgotRequestDto;
import com.instagram.instagrambe.dto.auth.LoginRequestDto;
import com.instagram.instagrambe.dto.auth.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto dto);
    void sendEmailResetPasswordOtp(String email);
    void resetPassword(String email,ForgotRequestDto dto);
}
