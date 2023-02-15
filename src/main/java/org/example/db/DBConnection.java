package org.example.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static String driver=null;
    private static String url=null;
    private static String user=null;
    private static String password=null;
    static{
        Properties properties=new Properties();
        try {
            FileReader fileReader=new FileReader("src/main/resources/database.configure");
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            properties.load(bufferedReader);
            driver=properties.getProperty("db_driver");
            url=properties.getProperty("db_url");
            user=properties.getProperty("db_user");
            password=properties.getProperty("db_password");
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            System.out.println("database.configure dosyasından veriler alınırken bir sorun ile karşılaşıldı." +
                    "\nHata: " + e.getMessage());
        }
    }
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver bulunamadı !" +
                    "\nHata: " + e.getMessage());
        }
        try {
            connection= DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Bağlantı kurulurken bir hata ile karşılaşıldı !" +
                    "\nHata: " + e.getMessage());
        }
        return connection;
    }
    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("ResultSet kapatılırken bir sorun ile karşılaşıldı !" +
                        "\nHata: " + e.getMessage());
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("PreparedStatement kapatılırken bir sorun ile karşılaşıldı !" +
                        "\nHata: " + e.getMessage());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Database bağlantısı kapatılırken bir sorun ile karşılaşıldı !" +
                        "\nHata: " + e.getMessage());
            }
        }
    }
}
