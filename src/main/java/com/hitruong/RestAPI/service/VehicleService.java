package com.hitruong.RestAPI.service;

import com.hitruong.RestAPI.model.VehicleRequest;
import com.hitruong.RestAPI.model.VehicleResponse;

import java.util.List;

public interface VehicleService {
    VehicleResponse getVehicleById(long id);

    List<VehicleResponse> getAllVehicles();

    Long addVehicle(VehicleRequest vehicleRequest);

    Long updateVehicle(long id, VehicleRequest vehicleRequest);

    Long deleteById(long id);

    List<VehicleResponse> getVehiclesByBrand(String brandName);

    List<VehicleResponse> getVehiclesByYearOfManufacture(int year);

    List<VehicleResponse> getVehiclesByPrice(long min, long max);

    List<VehicleResponse> getVehiclesByOwner(String owner);

    List<VehicleResponse> getFilterVehicles();
}
