package org.example.model.queries;

public class UserQueries {
    public static final String list="SELECT * FROM user";
    public static final String listUserProductJoin="SELECT * FROM user " +
            "LEFT JOIN user_product_join ON user.id=user_product_join.user_id " +
            "LEFT JOIN product ON user_product_join.product_id=product.id " +
            "LEFT JOIN category ON product.category_id=category.id " +
            "LEFT JOIN brand ON product.brand_id=brand.id WHERE user.id=?";
    public static final String insertQuery="INSERT INTO user (name,surname,user_name,birthday) VALUES (?,?,?,?)";
    public static final String insertUserProductJoinQuery="INSERT INTO user_product_join (user_id,product_id) VALUES (?,?)";
    public static final String updateQuery="UPDATE user SET name=?,surname=?,user_name=?,birthday=? WHERE id=?";
    public static final String deleteQuery="DELETE FROM user WHERE id=?";
    public static final String findByIdQuery="SELECT * FROM user WHERE id=?";
}
