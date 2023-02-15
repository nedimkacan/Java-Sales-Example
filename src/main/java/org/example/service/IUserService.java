package org.example.service;

import org.example.model.UserModel;

import java.util.List;

public interface IUserService {
    UserModel insert(UserModel userModel);
    boolean userProductJoin(int userId,int productId);
    UserModel update(UserModel userModel);
    boolean delete(int id);
    UserModel findByUserId(int id);
    UserModel findListProductJoin(int id);
    List<UserModel> findUserList();
}
