package com.instagram.instagrambe.entity;


import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "block")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    @Id
    @UuidGenerator
    @Column(name = "block_id",length = 36,nullable = false)
    private String blockId;

    @ManyToOne
    @JoinColumn(name = "blocker",referencedColumnName = "user_id")
    private User blocker;

    @ManyToOne
    @JoinColumn(name = "blocked",referencedColumnName = "user_id")
    private User user;

    @Column(name = "block_date")
    private Date blockDate;
}
