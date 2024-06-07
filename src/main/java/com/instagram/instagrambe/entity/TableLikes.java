package com.instagram.instagrambe.entity;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableLikes {
    @Id
    @UuidGenerator
    @Column(name = "likes_id",length = 36,nullable = false)
    private String likesId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "like_date")
    private Date likeDate;
}
