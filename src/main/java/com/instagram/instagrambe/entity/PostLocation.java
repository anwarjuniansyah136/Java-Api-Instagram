package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLocation {
    @Id
    @UuidGenerator
    @Column(name = "post_location_id",length = 36,nullable = false)
    private String postLocationId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "location",referencedColumnName = "location_id")
    private Location location;
}
