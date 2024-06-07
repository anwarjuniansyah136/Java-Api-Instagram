package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_hashtag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostHashtag {
    @Id
    @UuidGenerator
    @Column(name = "post_hashtag_id",length = 36,nullable = false)
    private String postHashtagId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "hashtag",referencedColumnName = "hashtag_id")
    private Hashtag hashtag;
}
