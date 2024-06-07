package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "highlight")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Highlight {
    @Id
    @UuidGenerator
    @Column(name = "highlight_id",length = 36,nullable = false)
    private String highlightId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "highight_name")
    private String highlightName;

    @Column(name = "created_date")
    private Date createdDate;
}
