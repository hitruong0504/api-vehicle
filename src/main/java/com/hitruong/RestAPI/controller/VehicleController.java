package com.hitruong.RestAPI.controller;

import com.hitruong.RestAPI.model.VehicleRequest;
import com.hitruong.RestAPI.model.VehicleResponse;
import com.hitruong.RestAPI.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/alls")
    public ResponseEntity<List<VehicleResponse>> getAllVehicles(){
        return new ResponseEntity<>(
                vehicleService.getAllVehicles(),
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
    public ResponseEntity<List<VehicleResponse>> filterVehicles(
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Long min, Long max,
            @RequestParam(required = false) String ownerName
    ){
        return new ResponseEntity<>(
                vehicleService.filterVehicles(brandName, year, min, max, ownerName),
                HttpStatus.OK
        );
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<VehicleResponse>> filteredVehicles(){
        return new ResponseEntity<>(
                vehicleService.getFilterVehicles(),
                HttpStatus.OK
        );
    }

}
