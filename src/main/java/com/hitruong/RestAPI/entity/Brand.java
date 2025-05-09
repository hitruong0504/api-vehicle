package com.hitruong.RestAPI.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long Id;

    @Column(name = "BRAND_NAME", nullable = false)
    private String name;
    @Column(name = "TYPE")
    private String type;
}
