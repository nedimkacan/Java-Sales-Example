package org.example.service;

import org.example.model.BrandModel;

import java.util.List;

public interface IBrandService {
    BrandModel findByBrandId(int id);
    List<BrandModel> findBrandList();
}
