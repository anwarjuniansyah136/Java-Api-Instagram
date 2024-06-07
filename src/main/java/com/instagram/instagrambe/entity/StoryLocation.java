package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "story_location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryLocation {
    @Id
    @UuidGenerator
    @Column(name = "story_location_id",length = 36,nullable = false)
    private String storyLocationId;

    @ManyToOne
    @JoinColumn(name = "story",referencedColumnName = "story_id")
    private Story story;

    @ManyToOne
    @JoinColumn(name = "location",referencedColumnName = "location_id")
    private Location location;
}
