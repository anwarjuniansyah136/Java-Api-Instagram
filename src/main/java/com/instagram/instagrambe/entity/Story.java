package com.instagram.instagrambe.entity;

import java.sql.Blob;
import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "story")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Story {
    @Id
    @UuidGenerator
    @Column(name = "story_id",length = 36,nullable = false)
    private String StoryId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "media_url")
    private Blob mediaUrl;

    @Column(name = "caption")
    private String caption;

    @Column(name = "story_date")
    private Date storyDate;

    @Column(name = "views_count")
    private int viewsCount;

    @Column(name = "expires_at")
    private Date expiresAt;
}
