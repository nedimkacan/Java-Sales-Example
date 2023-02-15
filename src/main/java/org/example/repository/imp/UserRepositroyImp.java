package org.example.repository.imp;

import org.example.db.DBConnection;
import org.example.model.BrandModel;
import org.example.model.CategoryModel;
import org.example.model.ProductModel;
import org.example.model.UserModel;
import org.example.model.queries.UserQueries;
import org.example.repository.IUserRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepositroyImp implements IUserRepository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public UserModel insert(UserModel userModel) {
        connection= DBConnection.getConnection();
        try {
            preparedStatement=connection.prepareStatement(UserQueries.insertQuery);
            preparedStatement.setString(1,userModel.getName());
            preparedStatement.setString(2,userModel.getSurname());
            preparedStatement.setString(3,userModel.getUserName());
            preparedStatement.setDate(4, Date.valueOf(userModel.getBirthday()));
            preparedStatement.executeUpdate();
            System.out.println("User başarılı bir şekilde kayıt edildi.");
        } catch (SQLException e) {
            System.out.println("User kayıt edilirken bir hata ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,null);
        }
        return userModel;
    }

    @Override
    public boolean userProductJoin(int userId, int productId) {
        connection=DBConnection.getConnection();
        try {
            preparedStatement=connection.prepareStatement(UserQueries.insertUserProductJoinQuery);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,productId);
            preparedStatement.executeUpdate();
            System.out.println("UserProductJoin başarılı bir şekilde kayıt edildi.");
        } catch (SQLException e) {
            System.out.println("UserProductJoin kayıt edilirken bir sorun ile karşılaşıldı." +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,null);
        }
        return true;
    }

    @Override
    public UserModel update(UserModel userModel) {
        connection=DBConnection.getConnection();
        try {
            preparedStatement=connection.prepareStatement(UserQueries.updateQuery);
            preparedStatement.setString(1,userModel.getName());
            preparedStatement.setString(2,userModel.getSurname());
            preparedStatement.setString(3,userModel.getUserName());
            preparedStatement.setDate(4, Date.valueOf(userModel.getBirthday()));
            preparedStatement.setInt(5,userModel.getId());
            preparedStatement.executeUpdate();
            System.out.println("User başarılı bir şekilde güncellendi.");
        } catch (SQLException e) {
            System.out.println("User güncellenirken bir sorun ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,null);
        }
        return userModel;
    }

    @Override
    public boolean delete(int id) {
        connection=DBConnection.getConnection();
        try {
            preparedStatement=connection.prepareStatement(UserQueries.deleteQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Silme işlemi başarılı bir şekilde gerçekleşti.");
        } catch (SQLException e) {
            System.out.println("User silme sırasında bir sorun ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,null);
        }
        return true;
    }

    @Override
    public UserModel findByUserId(int id) {
        connection=DBConnection.getConnection();
        UserModel userModel = null;
        try {
            preparedStatement=connection.prepareStatement(UserQueries.findByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String surname=resultSet.getString("surname");
                String userName=resultSet.getString("user_name");
                LocalDate birthday=resultSet.getDate("birthday").toLocalDate();
                userModel=new UserModel(userId,name,surname,userName,birthday);
            }
            System.out.println("User Id başarılı bir şekilde alındı.");
        } catch (SQLException e) {
            System.out.println("User Id alınırken bir sorun ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,null);
        }
        return userModel;
    }

    @Override
    public UserModel findListProductJoin(int id) {
        connection=DBConnection.getConnection();
        UserModel userModel=null;
        try {
            preparedStatement=connection.prepareStatement(UserQueries.listUserProductJoin);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            boolean status=true;
            List<ProductModel> productModels=new ArrayList<>();
            while (resultSet.next()){
                if (status) {
                    int userId=resultSet.getInt("user.id");
                    String userName=resultSet.getString("user.name");
                    String userSurname=resultSet.getString("user.surname");
                    String userUserName=resultSet.getString("user.user_name");
                    LocalDate userBirthday=resultSet.getDate("user.birthday").toLocalDate();
                    userModel=new UserModel(userId,userName,userSurname,userUserName,userBirthday);
                    status=false;
                }
                int productId=resultSet.getInt("product.id");
                String productName=resultSet.getString("product.name");
                double productPrice=resultSet.getDouble("product.price");
                int productStock=resultSet.getInt("product.stock");
                LocalDateTime productCreatedAt=resultSet.getDate("product.created_at").toLocalDate().atTime(LocalTime.now());
                LocalDateTime productUpdatedAt=resultSet.getDate("product.updated_at").toLocalDate().atTime(LocalTime.now());
                int categoryId=resultSet.getInt("category.id");
                String categoryName=resultSet.getString("category.name");
                int brandId=resultSet.getInt("brand.id");
                String brandName=resultSet.getString("brand.name");
                CategoryModel categoryModel=new CategoryModel(categoryId,categoryName);
                BrandModel brandModel=new BrandModel(brandId,brandName);
                ProductModel productModel=new ProductModel(productId,productName,productPrice,productStock,productCreatedAt, productUpdatedAt,categoryModel,brandModel);
                productModels.add(productModel);
            }
            userModel.setProductModels(productModels);
            System.out.println("UserProductJoin kaydı başarılı bir şekilde alındı.");
        } catch (SQLException e) {
            System.out.println("UserProductJoin kaydı alınırken bir sorun ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return userModel;
    }

    @Override
    public List<UserModel> findUserList() {
        connection=DBConnection.getConnection();
        List<UserModel> userModels=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(UserQueries.list);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int userId=resultSet.getInt("user.id");
                String userName=resultSet.getString("user.name");
                String userSurname=resultSet.getString("user.surname");
                String userUserName=resultSet.getString("user.user_name");
                LocalDate userBirthday=resultSet.getDate("user.birthday").toLocalDate();
                UserModel userModel=new UserModel(userId,userName,userSurname,userUserName,userBirthday);
                userModels.add(userModel);
            }
        } catch (SQLException e) {
            System.out.println("User kayıtları listelenirken bir hata ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return userModels;
    }
}
