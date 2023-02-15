package org.example.model.queries;

public class ProductQueries {
    public static final String listQuery="SELECT * FROM product " +
            "LEFT JOIN category ON product.category_id=category.id " +
            "LEFT JOIN brand ON product.brand_id=brand.id";
    public static final String insertQuery="INSERT INTO product (name,price,stock,created_at,updated_at,category_id,brand_id) VALUES (?,?,?,?,?,?,?)";
    public static final String updateQuery="UPDATE product SET name=?,price=?,stock=?,updated_at=?,category_id=?,brand_id=? WHERE id=?";
    public static final String deleteQuery="DELETE FROM product WHERE id=?";
    public static final String findByIdQuery="SELECT * FROM product " +
            "LEFT JOIN category ON product.category_id=category.id " +
            "Left JOIN brand ON product.brand_id=brand.id WHERE product.id=?";
}
