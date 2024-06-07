package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "collection_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionPost {
    @Id
    @UuidGenerator
    @Column(name = "collection_post_id",length = 36,nullable = false)
    private String collectionPostId;

    @ManyToOne
    @JoinColumn(name = "collection",referencedColumnName = "collection_id")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;
}
