package com.alimama.easybuy.user.service;

import com.alimama.easybuy.to.CommonResult;
import com.alimama.easybuy.user.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    //获取用户列表
    List<User> getUserList();
    //查找用户登入
    User Login(String name) throws SQLException;
    //用户注册
    CommonResult register(User user);
}
