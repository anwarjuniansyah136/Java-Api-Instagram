package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "highlight_story")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighlightStory {
    @Id
    @UuidGenerator
    @Column(name = "highlight_story_id",length = 36,nullable = false)
    private String highlightStoryId;

    @ManyToOne
    @JoinColumn(name = "highlight",referencedColumnName = "highlight_id")
    private Highlight highight;

    @ManyToOne
    @JoinColumn(name = "story",referencedColumnName = "story_id")
    private Story story;
}
