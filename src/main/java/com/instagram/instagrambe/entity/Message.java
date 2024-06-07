package com.instagram.instagrambe.entity;



import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @UuidGenerator
    @Column(name = "message_id",length = 36,nullable = false)
    private String messageId;
    
    @ManyToOne
    @JoinColumn(name = "sender",referencedColumnName = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receive",referencedColumnName = "user_id")
    private User receive;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "message_date")
    private Date messageDate;

    @Column(name = "is_read")
    private boolean isRead;
}
