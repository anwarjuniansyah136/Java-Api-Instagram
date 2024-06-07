package com.instagram.instagrambe.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @UuidGenerator
    @Column(name = "location_id",length = 36,nullable = false)
    private String locationId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;
}
