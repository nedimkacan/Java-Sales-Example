package org.example.service.imp;

import org.example.model.CategoryModel;
import org.example.repository.ICategoryRepository;
import org.example.repository.imp.CategoryRepositoryImp;
import org.example.service.ICategoryService;

import java.util.List;

public class CategoryServiceImp implements ICategoryService {
    private ICategoryRepository iCategoryRepository=new CategoryRepositoryImp();

    @Override
    public CategoryModel findByCategoryId(int id) {
        return iCategoryRepository.findByCategoryId(id);
    }

    @Override
    public List<CategoryModel> findCategoryList() {
        return iCategoryRepository.findCategoryList();
    }
}
