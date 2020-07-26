package top.quhailong.operation.service;

import top.quhailong.operation.entity.User;

public interface IUserService {
    void updateUserHandle(User user) throws InterruptedException;
    void saveUserHandle(User user);
}
