package org.example.repository.imp;

import org.example.db.DBConnection;
import org.example.model.BrandModel;
import org.example.model.queries.BrandQueries;
import org.example.repository.IBrandRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandRepositoryImp implements IBrandRepository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public BrandModel findByBrandId(int id) {
        connection= DBConnection.getConnection();
        BrandModel brandModel = null;
        try {
            preparedStatement=connection.prepareStatement(BrandQueries.findByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                int brandId=resultSet.getInt("id");
                String brandName=resultSet.getString("name");
                brandModel=new BrandModel(brandId,brandName);
            }
            System.out.println("Brand Id başarılı bir şekilde alındı.");
        } catch (SQLException e) {
            System.out.println("Brand Id alınırken bir sorun ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(connection,preparedStatement,resultSet);
        }
        return brandModel;
    }

    @Override
    public List<BrandModel> findBrandList() {
        connection=DBConnection.getConnection();
        List<BrandModel> brandModels=new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement(BrandQueries.listQuery);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int brandId=resultSet.getInt("id");
                String brandName=resultSet.getString("name");
                BrandModel brandModel=new BrandModel(brandId,brandName);
                brandModels.add(brandModel);
            }
            System.out.println("Brand kayıtları başarılı bir şekilde alındı.");
        } catch (SQLException e) {
            System.out.println("Brand kayıtları listelenirken bir hata ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        }
        return brandModels;
    }
}
