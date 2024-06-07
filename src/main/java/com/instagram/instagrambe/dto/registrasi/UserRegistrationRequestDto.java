package com.instagram.instagrambe.dto.registrasi;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestDto {
    private String username;
    private String email;
    private String password;
}
