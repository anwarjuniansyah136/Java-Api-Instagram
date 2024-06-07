package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @UuidGenerator
    @Column(name = "notification_id",length = 36,nullable = false)
    private String notificationId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "type")
    private String type;

    @Column(name = "related_id")
    private String relatedId;

    @Column(name = "message")
    private String message;

    @Column(name = "is_read")
    private boolean is_read;

    @Column(name = "notification_date")
    private Date notificationDate;
}
