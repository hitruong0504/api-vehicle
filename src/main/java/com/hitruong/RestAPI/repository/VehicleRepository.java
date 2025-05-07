package com.hitruong.RestAPI.repository;

import com.hitruong.RestAPI.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByBrandId(long id);

    List<Vehicle> findByYearOfManufacture(int yearOfManufacture);

    List<Vehicle> findByPriceBetween(long priceAfter, long priceBefore);

    List<Vehicle> findByOwner(String owner);
}
