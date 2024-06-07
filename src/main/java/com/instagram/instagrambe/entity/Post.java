package com.instagram.instagrambe.entity;

import java.sql.Blob;
import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @UuidGenerator
    @Column(name = "post_id",length = 36,nullable = false)
    private String postId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "caption")
    private String caption;

    @Lob
    @Column(name = "img_url")
    private Blob imgUrl;

    @Lob
    @Column(name = "video_url")
    private Blob videoUrl;

    @Column(name = "post_date")
    private Date postDate;

    @Column(name = "likes_count")
    private int likesCount;

    @Column(name = "comments_count")
    private int commentCount;
}
