package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "poll")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poll {
    @Id
    @UuidGenerator
    @Column(name = "poll_id",length = 36,nullable = false)
    private String pollId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "story",referencedColumnName = "story_id")
    private Story story;

    @Column(name = "question")
    private String question;

    @Column(name = "created_date")
    private Date createdDate;
}
