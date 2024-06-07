package com.instagram.instagrambe.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "poll_option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollOption {
    @Id
    @UuidGenerator
    @Column(name = "poll_option_id",length = 36,nullable = false)
    private String pollOptionId;

    @ManyToOne
    @JoinColumn(name = "poll",referencedColumnName = "poll_id")
    private Poll poll;

    @Column(name = "option_text")
    private String pollText;
}
