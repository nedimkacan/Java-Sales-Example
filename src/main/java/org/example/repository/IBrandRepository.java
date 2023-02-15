package org.example.repository;

import org.example.model.BrandModel;

import java.util.List;

public interface IBrandRepository {
    BrandModel findByBrandId(int id);
    List<BrandModel> findBrandList();
}
