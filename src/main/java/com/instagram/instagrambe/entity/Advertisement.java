package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "advertisement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    @Id
    @UuidGenerator
    @Column(name = "advertisement_id",length = 36,nullable = false)
    private String advertisementId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "media_url")
    private String mediaUrl;

    @Column(name = "caption")
    private String caption;

    @Column(name = "target_url")
    private String targetUrl;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "impression_count")
    private int impressionCount;

    @Column(name = "clicks_count")
    private int clicksCount;
}
