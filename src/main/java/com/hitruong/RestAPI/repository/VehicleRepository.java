package com.hitruong.RestAPI.repository;

import com.hitruong.RestAPI.entity.Vehicle;
import com.hitruong.RestAPI.model.VehicleFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("""
    SELECT v FROM Vehicle v
    WHERE (:#{#filter.brandName} IS NULL OR LOWER(v.brand.name) = LOWER(:#{#filter.brandName}))
      AND (:#{#filter.year} IS NULL OR v.yearOfManufacture = :#{#filter.year})
      AND (:#{#filter.minPrice} IS NULL OR v.price >= :#{#filter.minPrice})
      AND (:#{#filter.maxPrice} IS NULL OR v.price <= :#{#filter.maxPrice})
      AND (:#{#filter.ownerName} IS NULL OR v.owner = :#{#filter.ownerName})
""")
    Page<Vehicle> filterVehicles(
            @Param("filter") VehicleFilterRequest filter,
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
