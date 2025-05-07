package com.hitruong.RestAPI.service;

import com.hitruong.RestAPI.entity.Brand;
import com.hitruong.RestAPI.entity.Vehicle;
import com.hitruong.RestAPI.exception.CustomException;
import com.hitruong.RestAPI.model.VehicleRequest;
import com.hitruong.RestAPI.model.VehicleResponse;
import com.hitruong.RestAPI.repository.BrandRepository;
import com.hitruong.RestAPI.repository.VehicleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public VehicleResponse getVehicleById(long id) {
        Vehicle vehicle
                = vehicleRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        "VEHICLE_NOT_FOUND",
                        "Vehicle not found"
                ));

        VehicleResponse vehicleResponse
                = VehicleResponse.builder()
                .created(vehicle.getCreated())
                .name(vehicle.getName())
                .price(vehicle.getPrice())
                .owner(vehicle.getOwner())
                .yearOfManufacture(vehicle.getYearOfManufacture())
                .build();
        return vehicleResponse;
    }

    @Override
    public List<VehicleResponse> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(vehicle -> new VehicleResponse(
                        vehicle.getName(),
                        vehicle.getYearOfManufacture(),
                        vehicle.getPrice(),
                        vehicle.getOwner(),
                        vehicle.getCreated()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Long addVehicle(VehicleRequest vehicleRequest) {
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

        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleRequest.getName());
        vehicle.setYearOfManufacture(vehicleRequest.getYearOfManufacture());
        vehicle.setPrice(vehicleRequest.getPrice());
        vehicle.setOwner(vehicleRequest.getOwner());
        vehicle.setCreated(Instant.now());
        vehicle.setBrand(brand);

        vehicleRepository.save(vehicle);
        return vehicle.getId();
    }

    @Override
    public Long updateVehicle(long id, VehicleRequest vehicleRequest) {
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

        vehicle.setName(vehicleRequest.getName());
        vehicle.setYearOfManufacture(vehicleRequest.getYearOfManufacture());
        vehicle.setPrice(vehicleRequest.getPrice());
        vehicle.setOwner(vehicleRequest.getOwner());
        vehicle.setCreated(Instant.now());
        vehicle.setBrand(brand);
        vehicleRepository.save(vehicle);
        return vehicle.getId();
    }

    @Override
    public Long deleteById(long id) {
        Vehicle vehicle
                = vehicleRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        "VEHICLE_NOT_FOUND",
                        "Vehicle Not Found"
                ));

        vehicleRepository.delete(vehicle);
        return vehicle.getId();
    }

    @Override
    public List<VehicleResponse> getVehiclesByBrand(String brandName) {
        Brand brand
                = brandRepository.findByName(brandName)
                .orElseThrow(() -> new CustomException(
                        "BRAND_NAME_NOT_FOUND",
                        "Brand Name Not Found"
                ));

        List<Vehicle> vehicles = vehicleRepository.findByBrandId(brand.getId());
        List<VehicleResponse> vehicleResponses
                = vehicles.stream()
                        .map(vehicle -> VehicleResponse.builder()
                                .yearOfManufacture(vehicle.getYearOfManufacture())
                                .price(vehicle.getPrice())
                                .owner(vehicle.getOwner())
                                .created(vehicle.getCreated())
                                .name(vehicle.getName())
                                .build()).toList();
        return vehicleResponses;
    }

    @Override
    public List<VehicleResponse> getVehiclesByYearOfManufacture(int year) {
        List<Vehicle> vehicles = vehicleRepository.findByYearOfManufacture(year);
        if (vehicles.isEmpty()) {
            return new ArrayList<>();
        }
        List<VehicleResponse> vehicleResponses
                = vehicles.stream()
                .map(vehicle -> VehicleResponse.builder()
                        .name(vehicle.getName())
                        .yearOfManufacture(vehicle.getYearOfManufacture())
                        .price(vehicle.getPrice())
                        .owner(vehicle.getOwner())
                        .created(vehicle.getCreated())
                        .build()).toList();
        return vehicleResponses;
    }

    @Override
    public List<VehicleResponse> getVehiclesByPrice(long min, long max) {
        List<Vehicle> vehicles = vehicleRepository.findByPriceBetween(min, max);
        return getVehicleResponses(vehicles);
    }

    @Override
    public List<VehicleResponse> getVehiclesByOwner(String name) {
        List<Vehicle> vehicles = vehicleRepository.findByOwner(name);
        return getVehicleResponses(vehicles);
    }

    @Override
    public List<VehicleResponse> getFilterVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<Vehicle> filtered = vehicles.stream()
                .filter(v ->
                        (v.getPrice() > 10_000_000 && v.getBrand().getName().startsWith("S")) ||
                                (v.getPrice() <= 10_000_000 && "BUS".equalsIgnoreCase(v.getBrand().getType()))
                ).toList();

        List<VehicleResponse> responses
                = filtered.stream()
                .map(f -> VehicleResponse.builder()
                        .yearOfManufacture(f.getYearOfManufacture())
                        .name(f.getName())
                        .created(f.getCreated())
                        .price(f.getPrice())
                        .owner(f.getOwner())
                        .build()).toList();
        return responses;
    }

    private List<VehicleResponse> getVehicleResponses(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            return new ArrayList<>();
        }
        List<VehicleResponse> vehicleResponses
                = vehicles.stream()
                .map(vehicle -> VehicleResponse.builder()
                        .name(vehicle.getName())
                        .price(vehicle.getPrice())
                        .owner(vehicle.getOwner())
                        .created(vehicle.getCreated())
                        .yearOfManufacture(vehicle.getYearOfManufacture())
                        .build()).toList();
        return vehicleResponses;
    }

}
