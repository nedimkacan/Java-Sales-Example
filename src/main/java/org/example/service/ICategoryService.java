package org.example.service;

import org.example.model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    CategoryModel findByCategoryId(int id);
    List<CategoryModel> findCategoryList();
}
