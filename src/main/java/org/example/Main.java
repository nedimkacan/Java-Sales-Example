package org.example;

import org.example.model.BrandModel;
import org.example.model.CategoryModel;
import org.example.model.ProductModel;
import org.example.model.UserModel;
import org.example.service.IBrandService;
import org.example.service.ICategoryService;
import org.example.service.IProductService;
import org.example.service.IUserService;
import org.example.service.imp.BrandServiceImp;
import org.example.service.imp.CategoryServiceImp;
import org.example.service.imp.ProductServiceImp;
import org.example.service.imp.UserServiceImp;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // ========================== BRAND LIST TEST ==========================
        IBrandService iBrandService=new BrandServiceImp();
        List<BrandModel> brandModelList=iBrandService.findBrandList();
        for (BrandModel brandModel:brandModelList) {
            System.out.println("Brand Id: " + brandModel.getId()+
                    " Brand Name: " + brandModel.getName());
        }
        System.out.println();

        BrandModel brandModel=iBrandService.findByBrandId(2);
        System.out.println("Brand Id: " + brandModel.getId() +
                " Brand Name: " + brandModel.getName());
        System.out.println();

        // ========================== CATEGORY LIST TEST ==========================
        ICategoryService iCategoryService=new CategoryServiceImp();
        List<CategoryModel> categoryModelList=iCategoryService.findCategoryList();
        for (CategoryModel categoryModel:categoryModelList) {
            System.out.println("Category Id: " + categoryModel.getId()+
                    " Category Name: " + categoryModel.getName());
        }
        System.out.println();

        CategoryModel categoryModel=iCategoryService.findByCategoryId(2);
        System.out.println("Category Id: " + categoryModel.getId() +
                " Category Name: " + categoryModel.getName());
        System.out.println();

        // ========================== PRODUCT LIST TEST ==========================
        IProductService iProductService=new ProductServiceImp();
        List<ProductModel> productModelList=iProductService.findProductList();
        for (ProductModel productModel:productModelList) {
            System.out.println("Product Id: " + productModel.getId() +
                    " Product Name: " + productModel.getName() +
                    " Product Price: " + productModel.getPrice() +
                    " Product Stock: " + productModel.getStock() +
                    " Product Created At: " + productModel.getCreated_at() +
                    " Product Updated At: " + productModel.getUpdated_at() +
                    " Product Category Id: " + productModel.getCategoryModel().getId() +
                    " Product Category Name: " + productModel.getCategoryModel().getName() +
                    " Product Brand Id: " + productModel.getBrandModel().getId() +
                    " Product Brand Name: " + productModel.getBrandModel().getName());
        }
        System.out.println();

        ProductModel productModel=iProductService.findByProductId(1);
        System.out.println("Product Id: " + productModel.getId() +
                " Product Name: " + productModel.getName() +
                " Product Price: " + productModel.getPrice() +
                " Product Stock: " + productModel.getStock() +
                " Product Created At: " + productModel.getCreated_at() +
                " Product Updated At: " + productModel.getUpdated_at() +
                " Product Category Id: " + productModel.getCategoryModel().getId() +
                " Product Category Name: " + productModel.getCategoryModel().getName() +
                " Product Brand Id: " + productModel.getBrandModel().getId() +
                " Product Brand Name: " + productModel.getBrandModel().getName());


        // ========================== USER LIST TEST ==========================
        IUserService iUserService=new UserServiceImp();
        List<UserModel> userModelList=iUserService.findUserList();
        for (UserModel userModel:userModelList) {
            System.out.println("User Id: " + userModel.getId() +
                    " User Name: " + userModel.getName() +
                    " User Surname: " + userModel.getSurname() +
                    " User User Name: " + userModel.getUserName() +
                    " User Birthday: " + userModel.getBirthday());
        }
        System.out.println();

        UserModel userModel=iUserService.findByUserId(1);
        System.out.println("User Id: " + userModel.getId() +
                " User Name: " + userModel.getName() +
                " User Surname: " + userModel.getSurname() +
                " User User Name: " + userModel.getUserName() +
                " User Birthday: " + userModel.getBirthday());
        System.out.println();

        // ========================== USER PRODUCT JOIN LIST TEST ==========================
        UserModel userModel1=iUserService.findListProductJoin(1);
        System.out.println("User Id: " + userModel.getId() +
                " User Name: " + userModel.getName() +
                " User Surname: " + userModel.getSurname() +
                " User User Name: " + userModel.getUserName() +
                " User Birthday: " + userModel.getBirthday());
        List<ProductModel> productModelList1=userModel1.getProductModels();
        System.out.println(userModel1.getUserName() + " kullanıcısının paylaştığı ürünler: ");
        for (ProductModel productModel1:productModelList1) {
            System.out.println("\tProduct Id: " + productModel.getId() +
                    " Product Name: " + productModel.getName() +
                    " Product Price: " + productModel.getPrice() +
                    " Product Stock: " + productModel.getStock() +
                    " Product Created At: " + productModel.getCreated_at() +
                    " Product Updated At: " + productModel.getUpdated_at() +
                    " Product Category Id: " + productModel.getCategoryModel().getId() +
                    " Product Category Name: " + productModel.getCategoryModel().getName() +
                    " Product Brand Id: " + productModel.getBrandModel().getId() +
                    " Product Brand Name: " + productModel.getBrandModel().getName());
        }
        // ========================== PRUDUCT INSERT TEST ==========================
        ProductModel productModel1=new ProductModel("Monster Abra",7500,50, LocalDateTime.now(),null,null,null);
        CategoryModel categoryModel1=iCategoryService.findByCategoryId(2);
        productModel1.setCategoryModel(categoryModel1);

        BrandModel brandModel1=iBrandService.findByBrandId(3);
        productModel1.setBrandModel(brandModel1);
        iProductService.insert(productModel1);

        // ========================== USER PRUDUCT INSERT TEST ==========================
        iUserService.userProductJoin(2,2);

        // ========================== PRUDUCT UPDATE TEST ==========================
        ProductModel productModel2=iProductService.findByProductId(2);
        productModel2.setUpdated_at(LocalDateTime.now());
        iProductService.update(productModel2);
    }
}