package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "story_view")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryView {
    @Id
    @UuidGenerator
    @Column(name = "story_view_id",length = 36,nullable = false)
    private String storyViewId;

    @ManyToOne
    @JoinColumn(name = "story",referencedColumnName = "story_id")
    private Story story;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "view_date")
    private Date viewDate;
}
