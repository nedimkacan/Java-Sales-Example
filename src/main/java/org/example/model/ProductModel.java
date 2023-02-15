package org.example.model;

import java.time.LocalDateTime;

public class ProductModel {
    private int id;
    private String name;
    private double price;
    private int stock;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private CategoryModel categoryModel;
    private BrandModel brandModel;

    public ProductModel() {

    }

    public ProductModel(String name, double price, int stock, LocalDateTime created_at, LocalDateTime updated_at, CategoryModel categoryModel, BrandModel brandModel) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.categoryModel = categoryModel;
        this.brandModel = brandModel;
    }

    public ProductModel(int id, String name, double price, int stock, LocalDateTime created_at, LocalDateTime updated_at, CategoryModel categoryModel, BrandModel brandModel) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.categoryModel = categoryModel;
        this.brandModel = brandModel;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public BrandModel getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(BrandModel brandModel) {
        this.brandModel = brandModel;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", categoryModel=" + categoryModel +
                ", brandModel=" + brandModel +
                '}';
    }
}
