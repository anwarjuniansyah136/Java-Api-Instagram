package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_view")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostView {
    @Id
    @UuidGenerator
    @Column(name = "post_view_id",length = 36,nullable = false)
    private String postViewId;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "view_date")
    private Date viewDate;
}
