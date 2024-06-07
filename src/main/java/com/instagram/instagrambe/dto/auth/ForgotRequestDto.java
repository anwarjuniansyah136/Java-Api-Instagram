package com.instagram.instagrambe.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotRequestDto {
    String oneTimePassword;
    String newPassword; 
}
