package com.hitruong.RestAPI.repository;

import com.hitruong.RestAPI.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("""
    SELECT v FROM Vehicle v
    WHERE (:brandName IS NULL OR LOWER(v.brand.name) = LOWER(:brandName))
      AND (:year IS NULL OR v.yearOfManufacture = :year)
      AND (:minPrice IS NULL OR v.price >= :minPrice)
      AND (:maxPrice IS NULL OR v.price <= :maxPrice)
      AND (:ownerName IS NULL OR v.owner = :ownerName)
""")
    Page<Vehicle> filterVehicles(
            @Param("brandName") String brandName,
            @Param("year") Integer year,
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice,
            @Param("ownerName") String ownerName,
            Pageable pageable
    );

    @Query("""
    SELECT v FROM Vehicle v
    WHERE 
      (v.price > 10000000 AND LOWER(v.brand.name) LIKE 's%')
      OR
      (v.price <= 10000000 AND UPPER(v.brand.type) = 'BUS')
""")
    Page<Vehicle> getFilteredVehicles(Pageable pageable);

}
