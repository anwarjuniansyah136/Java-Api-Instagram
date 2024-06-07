package com.instagram.instagrambe.entity;

import java.sql.Blob;
import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private static final long OTP_VALID_DURATION = 5 * 60 * 1000; 

    @Id
    @UuidGenerator
    @Column(name = "user_id",length = 36,nullable = false)
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "profile_ficture")
    private Blob profileFicture;

    @Column(name = "bio")
    private String bio;

    @Column(name = "followers_count")
    private int followersCount;

    @Column(name = "following_count")
    private int followingCount;

    @ManyToOne
    @JoinColumn(name = "role",referencedColumnName = "role_id")
    private Role role;

    @Column(name = "otp",length = 100)
    private String otp;

    @Column(name = "otp_req_time",length = 100)
    private Date otpReqTime;

    public boolean isOtpRequired(){
        if (this.getOtp() == null) return false; 
        long currentTimeInMilis = System.currentTimeMillis();
        long otpRequestTimeInMilis = this.otpReqTime.getTime();

        if (otpRequestTimeInMilis + OTP_VALID_DURATION < currentTimeInMilis) return false;
            
        return true;
        
    }
}
