package com.instagram.instagrambe.entity;
import java.sql.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "poll_vote")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollVote {
    @Id
    @UuidGenerator
    @Column(name = "poll_vote_id",length = 36,nullable =  false)
    private String pollVoteId;

    @ManyToOne
    @JoinColumn(name = "poll_option",referencedColumnName = "poll_option_id")
    private PollOption pollOption;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @Column(name = "vote_date")
    private Date voteDate;
}
