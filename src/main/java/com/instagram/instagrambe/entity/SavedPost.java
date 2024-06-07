package com.instagram.instagrambe.entity;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "saved_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedPost {
    @Id
    @UuidGenerator
    @Column(name = "saved_post_id",length = 36,nullable = false)
    private String savedPostId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @Column(name = "saved_date")
    private Date savedDate;
}
