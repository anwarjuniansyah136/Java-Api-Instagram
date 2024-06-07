package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "archive")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Archive {
    @Id
    @UuidGenerator
    @Column(name = "archive_id",length = 36,nullable = false)
    private String archiveId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @Column(name = "archive_date")
    private Date archiveDate;
}