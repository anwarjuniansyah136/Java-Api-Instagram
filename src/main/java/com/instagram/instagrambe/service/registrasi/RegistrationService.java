package com.instagram.instagrambe.service.registrasi;

import com.instagram.instagrambe.dto.registrasi.UserRegistrationRequestDto;
import com.instagram.instagrambe.entity.User;

public interface RegistrationService {
    User register(UserRegistrationRequestDto dto);
}
