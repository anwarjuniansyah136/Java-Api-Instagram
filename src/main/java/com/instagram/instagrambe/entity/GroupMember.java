package com.instagram.instagrambe.entity;
// package com.instagram.instagrambe.entity;

// import java.sql.Date;

// import org.hibernate.annotations.UuidGenerator;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Table(name = "group_member")
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class GroupMember {
//     @Id
//     @UuidGenerator
//     @Column(name = "group_member_id",length = 36,nullable = false)
//     private String groupMemberId;

//     @ManyToOne
//     @JoinColumn(name = "group",referencedColumnName = "group_id")
//     private Group group;

//     @ManyToOne
//     @JoinColumn(name = "user",referencedColumnName = "user_id")
//     private User user;

//     @Column(name = "joinned_date")
//     private Date joinnedDate;
// }
