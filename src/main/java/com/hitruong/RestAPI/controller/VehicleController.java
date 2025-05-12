package com.hitruong.RestAPI.controller;

import com.hitruong.RestAPI.model.VehicleFilterRequest;
import com.hitruong.RestAPI.model.VehicleRequest;
import com.hitruong.RestAPI.model.VehicleResponse;
import com.hitruong.RestAPI.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable long id){
        return new ResponseEntity<>(
                vehicleService.getVehicleById(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addVehicle(@RequestBody VehicleRequest vehicleRequest){
        return new ResponseEntity<>(
                vehicleService.addVehicle(vehicleRequest),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updateVehicle(@PathVariable long id, @RequestBody VehicleRequest vehicleRequest){
        return new ResponseEntity<>(
                vehicleService.updateVehicle(id, vehicleRequest),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteVehicle(@PathVariable long id){
        return new ResponseEntity<>(
                vehicleService.deleteById(id),
                HttpStatus.OK
        );
    }

    @GetMapping()
    public ResponseEntity<Page<VehicleResponse>> filterVehicles(
            @ModelAttribute VehicleFilterRequest vehicleFilterRequest,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(vehicleService.filterVehicles(vehicleFilterRequest, page, size));
    }

    @GetMapping("/filtered")
    public ResponseEntity<Page<VehicleResponse>> filteredVehicles(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(vehicleService.getFilterVehicles(page, size));
    }

}
