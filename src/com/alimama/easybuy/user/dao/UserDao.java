package com.alimama.easybuy.user.dao;

import com.alimama.easybuy.user.bean.User;
import com.alimama.easybuy.util.BaseDao;

import java.sql.Connection;
import java.util.List;

public interface UserDao{

    //获取用户列表
    List<User> getUserList();
    //查找用户登入
    User findUserLogin(String name);
}
