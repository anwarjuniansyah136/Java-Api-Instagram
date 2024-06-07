package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_setting")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSetting {
    @Id
    @UuidGenerator
    @Column(name = "user_setting_id",length = 36,nullable = false)
    private String userSettingId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "setting_key")
    private String settingKey;

    @Column(name = "setting_value")
    private String settingValue;
}
