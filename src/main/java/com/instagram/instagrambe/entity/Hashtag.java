package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hashtag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hashtag {
    @Id
    @UuidGenerator
    @Column(name = "hashtag_id",length = 36,nullable = false)
    private String hashtagId;

    @Column(name = "hashtag_text")
    private String hashtagText;
}
