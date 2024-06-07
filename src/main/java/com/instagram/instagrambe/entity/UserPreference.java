package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "user_preference")
public class UserPreference {
    @Id
    @UuidGenerator
    @Column(name = "user_preference_id",length = 36,nullable = false)
    private String userPreferenceId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "preference_key")
    private String preferenceKey;

    @Column(name = "preference_value")
    private String preferenceValue;
}
