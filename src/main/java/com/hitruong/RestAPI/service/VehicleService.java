package com.hitruong.RestAPI.service;

import com.hitruong.RestAPI.model.VehicleFilterRequest;
import com.hitruong.RestAPI.model.VehicleRequest;
import com.hitruong.RestAPI.model.VehicleResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleService {
    VehicleResponse getVehicleById(long id);

    Long addVehicle(VehicleRequest vehicleRequest);

    Long updateVehicle(long id, VehicleRequest vehicleRequest);

    Long deleteById(long id);

    Page<VehicleResponse> getFilterVehicles(int page, int size);

    Page<VehicleResponse> filterVehicles(VehicleFilterRequest vehicleFilterRequest, int page, int size);
}
