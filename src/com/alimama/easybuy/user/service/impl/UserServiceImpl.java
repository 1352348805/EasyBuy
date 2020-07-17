package com.alimama.easybuy.user.service.impl;

import com.alimama.easybuy.user.bean.User;
import com.alimama.easybuy.user.dao.UserDao;
import com.alimama.easybuy.user.dao.impl.UserDaoImpl;
import com.alimama.easybuy.user.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl {

//    @Override
//    public List<User> getUserList() {
//        List<User> userList = null;
//        Connection con = null;
//
//        try {
//
//            con = DBPool.getConnection();
//            UserDao dao = new UserDaoImpl(con);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }finally {
//            try {
//                con.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return userList;
//    }
//
//    @Override
//    public User findUserLogin(String name) throws SQLException {
//        Connection conn=null;
//        conn=DBPool.getConnection();
//        User user=null;
//        UserDao dao=new UserDaoImpl(conn);
//        user= dao.findUserLogin(name);
//        return user;
//    }
}
