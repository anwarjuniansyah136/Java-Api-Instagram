package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "explore")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Explore {
    @Id
    @UuidGenerator
    @Column(name = "explore_id",length = 36,nullable = false)
    private String exploreId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @Column(name = "category")
    private String category;

    @Column(name = "trending_score")
    private int trendingScore;
}
