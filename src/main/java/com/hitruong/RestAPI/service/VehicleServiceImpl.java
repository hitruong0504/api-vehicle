package com.hitruong.RestAPI.service;

import com.hitruong.RestAPI.entity.Brand;
import com.hitruong.RestAPI.entity.Vehicle;
import com.hitruong.RestAPI.exception.CustomException;
import com.hitruong.RestAPI.mapper.VehicleMapper;
import com.hitruong.RestAPI.model.VehicleRequest;
import com.hitruong.RestAPI.model.VehicleResponse;
import com.hitruong.RestAPI.repository.BrandRepository;
import com.hitruong.RestAPI.repository.VehicleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<VehicleResponse> getFilterVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<Vehicle> filtered = vehicles.stream()
                .filter(v ->
                        (v.getPrice() > 10_000_000 && v.getBrand().getName().startsWith("S")) ||
                                (v.getPrice() <= 10_000_000 && "BUS".equalsIgnoreCase(v.getBrand().getType()))
                ).toList();

        return filtered.stream()
                .map(vehicleMapper::toResponse)
                .toList();
    }

    @Override
    public List<VehicleResponse> filterVehicles(String brandName, Integer year, Long minPrice, Long maxPrice, String ownerName) {
        return vehicleRepository.findAll().stream()
                .filter(vehicle -> brandName == null || vehicle.getBrand().getName().equalsIgnoreCase(brandName))
                .filter(vehicle -> year == null || vehicle.getYearOfManufacture() == year)
                .filter(vehicle -> minPrice == null || vehicle.getPrice() >= minPrice)
                .filter(vehicle -> maxPrice == null || vehicle.getPrice() <= maxPrice)
                .filter(vehicle -> ownerName == null || vehicle.getOwner().equals(ownerName))
                .map(vehicleMapper::toResponse)
                .collect(Collectors.toList());
    }

}
