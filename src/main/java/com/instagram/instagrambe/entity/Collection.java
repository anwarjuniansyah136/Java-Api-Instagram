package com.instagram.instagrambe.entity;

import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection {
    @Id
    @UuidGenerator
    @Column(name = "collection_id",length = 36,nullable = false)
    private String collectionId;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "collection_name")
    private String collectionName;

    @Column(name = "collection_date")
    private Date collectionDate;
}
