package com.instagram.instagrambe.entity;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @UuidGenerator
    @Column(name = "comment_id",length = 36,nullable = false)
    private String commentId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "comment_date")
    private Date columnDate;
}
