package com.hitruong.RestAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRequest {
    private String name;
    private String owner;
    private String brandName;

    private Integer yearOfManufacture;
    private Long price;
}
