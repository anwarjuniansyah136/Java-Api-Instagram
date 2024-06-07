package com.instagram.instagrambe.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "story_analytics")
public class StoryAnalytics {
    @Id
    @UuidGenerator
    @Column(name = "story_analytics_id",length = 36,nullable = false)
    private String storyAnalyticsId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @Column(name = "views_count")
    private int viewsCount;

    @Column(name = "shares_count")
    private int sharesCount;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
