package com.hitruong.RestAPI.service;


import com.hitruong.RestAPI.entity.Brand;
import com.hitruong.RestAPI.exception.CustomException;
import com.hitruong.RestAPI.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService{


    @Autowired
    private BrandRepository brandRepository;


    @Override
    public Long deleteById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        "BRAND_NOT_FOUND",
                        "Brand Not Found"
                ));
        brand.setDeleted(true);
        brandRepository.save(brand);
        return brand.getId();
    }
}
