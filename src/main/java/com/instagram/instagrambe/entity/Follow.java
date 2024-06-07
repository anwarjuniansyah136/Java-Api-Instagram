package com.instagram.instagrambe.entity;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "follow")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @Id
    @UuidGenerator
    @Column(name = "follow_id",length = 36,nullable = false)
    private String followId;

    @ManyToOne
    @JoinColumn(name = "follower",referencedColumnName = "user_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following",referencedColumnName = "user_id")
    private User following;

    @Column(name = "follow_date")
    private Date followDate;
}
