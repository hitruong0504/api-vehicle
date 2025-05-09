package com.hitruong.RestAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "YEAR_OF_MANUFACTURE", nullable = true)
    private Integer yearOfManufacture;
    @Column(name = "PRICE", nullable = true)
    private Long price;
    @Column(name = "OWNER")
    private String owner;
    @Column(name = "CREATED")
    private Instant created;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;
}
