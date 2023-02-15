package org.example.repository.imp;

import org.example.db.DBConnection;
import org.example.model.CategoryModel;
import org.example.model.queries.CategoryQueries;
import org.example.repository.ICategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImp implements ICategoryRepository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public CategoryModel findByCategoryId(int id) {
        connection= DBConnection.getConnection();
        CategoryModel categoryModel = null;
        try {
            preparedStatement=connection.prepareStatement(CategoryQueries.findByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                int categoryId=resultSet.getInt("id");
                String categoryName=resultSet.getString("name");
                categoryModel=new CategoryModel(categoryId,categoryName);
            }
            System.out.println("Category Id başarılı bir şekilde alındı.");
        } catch (SQLException e) {
            System.out.println("Category Id alınırken bir sorun ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return categoryModel;
    }

    @Override
    public List<CategoryModel> findCategoryList() {
        connection=DBConnection.getConnection();
        List<CategoryModel> categoryModels=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(CategoryQueries.listQuery);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int categoryId=resultSet.getInt("id");
                String categoryName=resultSet.getString("name");
                CategoryModel categoryModel=new CategoryModel(categoryId,categoryName);
                categoryModels.add(categoryModel);
            }
            System.out.println("Category kayıtları başarılı bir şekilde alındı.");
        } catch (SQLException e) {
            System.out.println("Category kayıtları listelenirken bir hata ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return categoryModels;
    }
}
