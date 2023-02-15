package org.example.service.imp;

import org.example.model.ProductModel;
import org.example.repository.IProductRepository;
import org.example.repository.imp.ProductRepositoryImp;
import org.example.service.IProductService;

import java.util.List;

public class ProductServiceImp implements IProductService {
    private IProductRepository iProductRepository=new ProductRepositoryImp();

    @Override
    public ProductModel insert(ProductModel productModel) {
        return iProductRepository.insert(productModel);
    }

    @Override
    public boolean insertBathcProduct(List<ProductModel> productModels) {
        return iProductRepository.insertBathcProduct(productModels);
    }

    @Override
    public ProductModel update(ProductModel productModel) {
        return iProductRepository.update(productModel);
    }

    @Override
    public boolean delete(int id) {
        return iProductRepository.delete(id);
    }

    @Override
    public ProductModel findByProductId(int id) {
        return iProductRepository.findByProductId(id);
    }

    @Override
    public List<ProductModel> findProductList() {
        return iProductRepository.findProductList();
    }
}
