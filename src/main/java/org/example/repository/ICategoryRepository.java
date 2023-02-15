package org.example.repository;

import org.example.model.CategoryModel;

import java.util.List;

public interface ICategoryRepository {
    CategoryModel findByCategoryId(int id);
    List<CategoryModel> findCategoryList();
}
