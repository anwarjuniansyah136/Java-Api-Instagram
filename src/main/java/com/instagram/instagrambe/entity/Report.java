package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @UuidGenerator
    @Column(name = "report_id",length = 36,nullable = false)
    private String reportId;

    @ManyToOne
    @JoinColumn(name = "reported_by",referencedColumnName = "user_id")
    private User reportedBy;

    @ManyToOne
    @JoinColumn(name = "post",referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment",nullable = false)
    private Comment comment;

    @Column(name = "report_reason")
    private String reportReason;

    @Column(name = "report_date")
    private Date reportDate;

    @Column(name = "status")
    private String status;
}
