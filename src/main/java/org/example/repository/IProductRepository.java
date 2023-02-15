package org.example.repository;

import org.example.model.ProductModel;

import java.util.List;

public interface IProductRepository {
    ProductModel insert(ProductModel productModel);
    boolean insertBathcProduct(List<ProductModel> productModels);
    ProductModel update(ProductModel productModel);
    boolean delete(int id);
    ProductModel findByProductId(int id);
    List<ProductModel> findProductList();
}
