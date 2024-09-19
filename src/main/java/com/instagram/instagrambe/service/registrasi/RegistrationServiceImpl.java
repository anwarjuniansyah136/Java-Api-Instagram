package com.instagram.instagrambe.service.registrasi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.instagram.instagrambe.constant.RoleConstant;
import com.instagram.instagrambe.dto.registrasi.UserRegistrationRequestDto;
import com.instagram.instagrambe.entity.Role;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.RoleRepository;
import com.instagram.instagrambe.repository.UserRepository;
import com.instagram.instagrambe.service.EmailService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Override
    public User register(UserRegistrationRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()) == null) {
            User user = saveUser(dto);
            sendEmail(dto.getEmail());
            return user;
        }
        throw new RuntimeException("Email Telah Digunakan");
    }

    private User saveUser(UserRegistrationRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByRoleName(RoleConstant.USERS_ROLE);
        user.setRole(role);
        return userRepository.save(user);
    }

    private void sendEmail(String to) {
        String subject = "Customer Registration";
        String text = "Selamat datang di layanan kami!" +

                "Untuk melengkapi proses registrasi Anda, mohon klik tautan di bawah ini dan ikuti petunjuk untuk menyelesaikan pendaftaran akun email Anda:"
                +
                "[Tautan Registrasi]" +
                "Terima kasih atas kepercayaan Anda kepada kami. Jika Anda memerlukan bantuan lebih lanjut, jangan ragu untuk menghubungi tim dukungan kami."
                +
                "Salam hangat," +
                "[Tim Layanan Pelanggan]";
        emailService.sendSimpleMessage(to, subject, text);
    }
}
