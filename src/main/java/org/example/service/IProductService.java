package org.example.service;

import org.example.model.ProductModel;

import java.util.List;

public interface IProductService {
    ProductModel insert(ProductModel productModel);
    boolean insertBathcProduct(List<ProductModel> productModels);
    ProductModel update(ProductModel productModel);
    boolean delete(int id);
    ProductModel findByProductId(int id);
    List<ProductModel> findProductList();
}
