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
public class VehicleResponse {
    private String name;
    private int yearOfManufacture;
    private long price;
    private String owner;
    private Instant created;
}
