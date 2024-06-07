package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @UuidGenerator
    @Column(name = "role_id",length = 36,nullable = false)
    private String roleId;

    @Column(name = "role_name")
    private String roleName;
}
