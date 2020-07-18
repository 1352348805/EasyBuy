package com.alimama.easybuy.user.service;

import com.alimama.easybuy.user.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    //获取用户列表
    List<User> getUserList();
    //查找用户登入
    User findUserLogin(String name) throws SQLException;
    //用户注册
    Integer register(User user);
}
