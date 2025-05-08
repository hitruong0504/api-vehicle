package com.hitruong.RestAPI.model;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name of car is required.")
    private String name;
    @NotBlank(message = "Owner is required.")
    private String owner;
    @NotBlank(message = "Brand name is required.")
    private String brandName;

    private Integer yearOfManufacture;
    private Long price;
}
