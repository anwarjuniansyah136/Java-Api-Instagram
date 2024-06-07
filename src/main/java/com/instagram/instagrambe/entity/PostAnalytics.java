package com.instagram.instagrambe.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_analytics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostAnalytics {
    @Id
    @UuidGenerator
    @Column(name = "post_analytics_id",length = 36,nullable = false)
    private String postAnalyticsId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @Column(name = "views_count")
    private int viewsCount;

    @Column(name = "shares_count")
    private int sharesCount;

    @Column(name = "saves_count")
    private int savesCount;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
