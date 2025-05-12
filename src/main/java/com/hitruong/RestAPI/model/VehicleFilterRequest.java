package com.hitruong.RestAPI.model;


import lombok.Data;

@Data
public class VehicleFilterRequest {
    private String brandName;
    private Integer year;
    private Long minPrice;
    private Long maxPrice;
    private String ownerName;
}
