package com.hitruong.RestAPI.controller;

import com.hitruong.RestAPI.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> softDeleteBrandById(@PathVariable Long id){
        return new ResponseEntity<>(
                brandService.deleteById(id),
                HttpStatus.OK
        );
    }
}
