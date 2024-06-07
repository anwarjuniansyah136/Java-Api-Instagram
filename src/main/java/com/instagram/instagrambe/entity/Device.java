package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "device")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    @UuidGenerator
    @Column(name = "device_id",length = 36,nullable = false)
    private String deviceId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User post;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "last_login")
    private Date lastLogin;
}
