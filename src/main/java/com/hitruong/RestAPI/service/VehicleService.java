package com.hitruong.RestAPI.service;

import com.hitruong.RestAPI.model.VehicleRequest;
import com.hitruong.RestAPI.model.VehicleResponse;

import java.util.List;

public interface VehicleService {
    VehicleResponse getVehicleById(long id);

    Long addVehicle(VehicleRequest vehicleRequest);

    Long updateVehicle(long id, VehicleRequest vehicleRequest);

    Long deleteById(long id);

    List<VehicleResponse> getFilterVehicles();

    List<VehicleResponse> filterVehicles(String brandName, Integer year, Long min, Long max, String ownerName);
}
