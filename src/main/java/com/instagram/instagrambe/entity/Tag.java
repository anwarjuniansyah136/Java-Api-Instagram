package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @UuidGenerator
    @Column(name = "tag_id",length = 36,nullable =  false)
    private String tagId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment",referencedColumnName = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "tagged_user_id",referencedColumnName = "user_id")
    private User taggedUser;

    @ManyToOne
    @JoinColumn(name = "tagged_by_user",referencedColumnName = "user_id")
    private User taggedByUser;

    @Column(name = "tag_date")
    private Date tagDate;
}
