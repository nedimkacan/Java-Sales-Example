package org.example.model;

import java.time.LocalDate;
import java.util.List;

public class UserModel {
    private int id;
    private String name;
    private String surname;
    private String userName;
    private LocalDate birthday;
    private List<ProductModel> productModels;

    public UserModel() {

    }

    public UserModel(String name, String surname, String userName, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.birthday = birthday;
    }

    public UserModel(int id, String name, String surname, String userName, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName(){return userName;}

    public void setUserName(String userName){this.userName=userName;}

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
