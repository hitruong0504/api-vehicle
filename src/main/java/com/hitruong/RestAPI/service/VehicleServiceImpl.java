package com.hitruong.RestAPI.service;

import com.hitruong.RestAPI.entity.Brand;
import com.hitruong.RestAPI.entity.Vehicle;
import com.hitruong.RestAPI.exception.CustomException;
import com.hitruong.RestAPI.mapper.VehicleMapper;
import com.hitruong.RestAPI.model.VehicleFilterRequest;
import com.hitruong.RestAPI.model.VehicleRequest;
import com.hitruong.RestAPI.model.VehicleResponse;
import com.hitruong.RestAPI.repository.BrandRepository;
import com.hitruong.RestAPI.repository.VehicleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public VehicleResponse getVehicleById(long id) {
        log.info("Getting vehicle by id {}", id);
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        "VEHICLE_NOT_FOUND",
                        "Vehicle id " + id + " not found"
                ));
        log.info("Vehicle found {}", vehicle);
        return vehicleMapper.toResponse(vehicle);
    }


    @Override
    public Long addVehicle(VehicleRequest vehicleRequest) {
        log.info("Adding vehicle {}", vehicleRequest);
        Optional<Brand> existingBrand = brandRepository.findByName(vehicleRequest.getBrandName());

        Brand brand;
        if (existingBrand.isPresent()) {
            brand = existingBrand.get();
        }else{
            brand = Brand.builder()
                    .name(vehicleRequest.getBrandName())
                    .build();
            brandRepository.save(brand);
        }

        Vehicle vehicle = vehicleMapper.toEntity(vehicleRequest);
        vehicle.setBrand(brand);
        vehicle.setBrandNameSnapshot(brand.getName());
        vehicleRepository.save(vehicle);
        log.info("Vehicle added {}", vehicle);
        return vehicle.getId();
    }

    @Override
    public Long updateVehicle(long id, VehicleRequest vehicleRequest) {
        log.info("Updating vehicle {}", vehicleRequest);
        Optional<Brand> existingBrand = brandRepository.findByName(vehicleRequest.getBrandName());

        Brand brand;
        if (existingBrand.isPresent()) {
            brand = existingBrand.get();
        }else{
            brand = Brand.builder()
                    .name(vehicleRequest.getBrandName())
                    .build();
            brandRepository.save(brand);
        }

        Vehicle vehicle
                = vehicleRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        "VEHICLE_NOT_FOUND",
                        "Vehicle Not Found"
                ));

        vehicleMapper.updateEntityFromRequest(vehicleRequest, vehicle);
        vehicle.setBrand(brand);
        vehicle.setBrandNameSnapshot(brand.getName());
        vehicleRepository.save(vehicle);
        log.info("Vehicle updated {}", vehicle);
        return vehicle.getId();
    }

    @Override
    public Long deleteById(long id) {
        log.info("Deleting vehicle {}", id);
        Vehicle vehicle
                = vehicleRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        "VEHICLE_NOT_FOUND",
                        "Vehicle Not Found"
                ));

        vehicleRepository.delete(vehicle);
        log.info("Vehicle deleted {}", vehicle);
        return vehicle.getId();
    }

    @Override
    public Page<VehicleResponse> getFilterVehicles(int page, int size) {
        Page<Vehicle> vehicles = vehicleRepository.getFilteredVehicles(PageRequest.of(page, size));
        return vehicles.map(vehicleMapper::toResponse);
    }

    @Override
    public Page<VehicleResponse> filterVehicles(VehicleFilterRequest vehicleFilterRequest, int page, int size) {
        Page<Vehicle> vehicles = vehicleRepository.filterVehicles(
                vehicleFilterRequest.getBrandName(),
                vehicleFilterRequest.getYear(),
                vehicleFilterRequest.getMinPrice(),
                vehicleFilterRequest.getMaxPrice(),
                vehicleFilterRequest.getOwnerName(),
                PageRequest.of(page, size)
        );
        return vehicles.map(vehicleMapper::toResponse);
    }


}
