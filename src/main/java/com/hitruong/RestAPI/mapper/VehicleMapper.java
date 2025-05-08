package com.hitruong.RestAPI.mapper;

import com.hitruong.RestAPI.entity.Vehicle;
import com.hitruong.RestAPI.model.VehicleRequest;
import com.hitruong.RestAPI.model.VehicleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehicleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", expression = "java(java.time.Instant.now())")
    @Mapping(target = "brand", ignore = true)
    Vehicle toEntity(VehicleRequest vehicleRequest);

    VehicleResponse toResponse(Vehicle vehicle);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", expression = "java(java.time.Instant.now())")
    @Mapping(target = "brand", ignore = true)
    void updateEntityFromRequest(VehicleRequest vehicleRequest, @MappingTarget Vehicle vehicle);
}
