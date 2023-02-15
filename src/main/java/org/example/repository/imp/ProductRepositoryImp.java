package org.example.repository.imp;

import org.example.db.DBConnection;
import org.example.model.BrandModel;
import org.example.model.CategoryModel;
import org.example.model.ProductModel;
import org.example.model.queries.ProductQueries;
import org.example.repository.IProductRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImp implements IProductRepository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public ProductModel insert(ProductModel productModel) {
        connection= DBConnection.getConnection();
        try {
            LocalDateTime localDateTime= LocalDateTime.now();
            preparedStatement=connection.prepareStatement(ProductQueries.insertQuery);
            preparedStatement.setString(1,productModel.getName());
            preparedStatement.setDouble(2,productModel.getPrice());
            preparedStatement.setInt(3,productModel.getStock());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
            preparedStatement.setInt(6,productModel.getCategoryModel().getId());
            preparedStatement.setInt(7,productModel.getBrandModel().getId());
            preparedStatement.executeUpdate();
            System.out.println("Product başarılı bir şekilde kayıt edildi.");
        } catch (SQLException e) {
            System.out.println("Product kayıt edilirken bir hata ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,null);
        }
        return productModel;
    }

    @Override
    public boolean insertBathcProduct(List<ProductModel> productModels) {
        connection= DBConnection.getConnection();
        try {
            LocalDateTime localDateTime= LocalDateTime.now();
            preparedStatement=connection.prepareStatement(ProductQueries.insertQuery);
            for (ProductModel productModel:productModels) {
                preparedStatement.setString(1,productModel.getName());
                preparedStatement.setDouble(2,productModel.getPrice());
                preparedStatement.setInt(3,productModel.getStock());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
                preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
                preparedStatement.setInt(6,productModel.getCategoryModel().getId());
                preparedStatement.setInt(7,productModel.getBrandModel().getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("Productlar başarılı bir şekilde kayıt edildi.");
        } catch (SQLException e) {
            System.out.println("Productlar kayıt edilirken bir hata ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,null);
        }
        return true;
    }

    @Override
    public ProductModel update(ProductModel productModel) {
        connection=DBConnection.getConnection();
        try {
            LocalDateTime localDateTime=LocalDateTime.now();
            preparedStatement=connection.prepareStatement(ProductQueries.updateQuery);
            preparedStatement.setString(1,productModel.getName());
            preparedStatement.setDouble(2,productModel.getPrice());
            preparedStatement.setInt(3,productModel.getStock());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
            preparedStatement.setInt(5,productModel.getCategoryModel().getId());
            preparedStatement.setInt(6,productModel.getBrandModel().getId());
            preparedStatement.setInt(7,productModel.getId());
            preparedStatement.executeUpdate();
            System.out.println("Product başarılı bir şekilde güncellendi.");
        } catch (SQLException e) {
            System.out.println("Product güncellenirken bir sorun ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,null);
        }
        return productModel;
    }

    @Override
    public boolean delete(int id) {
        connection=DBConnection.getConnection();
        try {
            preparedStatement=connection.prepareStatement(ProductQueries.deleteQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Product silme işlemi başarılı bir şekilde gerçekleşti.");
        } catch (SQLException e) {
            System.out.println("Product silme sırasında bir sorun ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,null);
        }
        return true;
    }

    @Override
    public ProductModel findByProductId(int id) {
        connection=DBConnection.getConnection();
        ProductModel productModel = null;
        try {
            preparedStatement=connection.prepareStatement(ProductQueries.findByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                int productId=resultSet.getInt("product.id");
                String productName=resultSet.getString("product.name");
                double productPrice=resultSet.getDouble("product.price");
                int productStock=resultSet.getInt("product.stock");
                LocalDateTime productCreatedAt=resultSet.getDate("product.created_at").toLocalDate().atTime(LocalTime.now());
                LocalDateTime productUpdatedAt=resultSet.getDate("product.updated_at").toLocalDate().atTime(LocalTime.now());
                int categoryId=resultSet.getInt("product.category_id");
                String categoryName=resultSet.getString("category.name");
                int brandId=resultSet.getInt("product.brand_id");
                String brandName=resultSet.getString("brand.name");

                CategoryModel categoryModel=new CategoryModel(categoryId,categoryName);
                BrandModel brandModel=new BrandModel(brandId,brandName);
                productModel=new ProductModel(productId,productName,productPrice,productStock,productCreatedAt, productUpdatedAt,categoryModel,brandModel);
            }
            System.out.println("Product Id başarılı bir şekilde alındı.");
        } catch (SQLException e) {
            System.out.println("Product Id alınırken bir sorun ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return productModel;
    }

    @Override
    public List<ProductModel> findProductList() {
        connection=DBConnection.getConnection();
        List<ProductModel> productModels=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(ProductQueries.listQuery);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int productId=resultSet.getInt("product.id");
                String productName=resultSet.getString("product.name");
                double productPrice=resultSet.getDouble("product.price");
                int productStock=resultSet.getInt("product.stock");
                LocalDateTime productCreatedAt=resultSet.getDate("product.created_at").toLocalDate().atTime(LocalTime.now());
                LocalDateTime productUpdatedAt=resultSet.getDate("product.updated_at").toLocalDate().atTime(LocalTime.now());
                int categoryId=resultSet.getInt("product.category_id");
                String categoryName=resultSet.getString("category.name");
                int brandId=resultSet.getInt("product.brand_id");
                String brandName=resultSet.getString("brand.name");
                CategoryModel categoryModel=new CategoryModel(categoryId,categoryName);
                BrandModel brandModel=new BrandModel(brandId,brandName);
                ProductModel productModel=new ProductModel(productId,productName,productPrice,productStock,productCreatedAt,productUpdatedAt,categoryModel,brandModel);
                productModels.add(productModel);
            }
            System.out.println("Product kayıtları başarılı bir şekilde listelendi.");
        } catch (SQLException e) {
            System.out.println("Product kayıtları listelenirken bir hata ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return productModels;
    }
}
