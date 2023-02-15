package org.example.service.imp;

import org.example.model.UserModel;
import org.example.repository.IUserRepository;
import org.example.repository.imp.UserRepositroyImp;
import org.example.service.IUserService;

import java.util.List;

public class UserServiceImp implements IUserService {
    private IUserRepository iUserRepository=new UserRepositroyImp();

    @Override
    public UserModel insert(UserModel userModel) {
        return iUserRepository.insert(userModel);
    }

    @Override
    public boolean userProductJoin(int userId, int productId) {
        return iUserRepository.userProductJoin(userId,productId);
    }

    @Override
    public UserModel update(UserModel userModel) {
        return iUserRepository.update(userModel);
    }

    @Override
    public boolean delete(int id) {
        return iUserRepository.delete(id);
    }

    @Override
    public UserModel findByUserId(int id) {
        return iUserRepository.findByUserId(id);
    }

    @Override
    public UserModel findListProductJoin(int id) {
        return iUserRepository.findListProductJoin(id);
    }

    @Override
    public List<UserModel> findUserList() {
        return iUserRepository.findUserList();
    }
}
