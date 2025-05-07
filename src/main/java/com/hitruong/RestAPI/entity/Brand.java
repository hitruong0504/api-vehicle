package com.hitruong.RestAPI.entity;


import com.hitruong.RestAPI.model.BrandType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "BRAND_NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;
}
