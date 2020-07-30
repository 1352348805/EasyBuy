package com.alimama.easybuy.user.dao;

import com.alimama.easybuy.user.bean.User;
import com.alimama.easybuy.util.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao{

    //获取用户列表
    List<User> getUserList();
    //查找用户登入
    User findUserLogin(String name);
    //添加用户
    Integer insert(User user) throws SQLException;

}
