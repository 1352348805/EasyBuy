package com.alimama.easybuy.user.service.impl;

import com.alimama.easybuy.to.CommonResult;
import com.alimama.easybuy.user.bean.User;
import com.alimama.easybuy.user.dao.UserDao;
import com.alimama.easybuy.user.dao.impl.UserDaoImpl;
import com.alimama.easybuy.user.service.UserService;
import com.alimama.easybuy.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{

    @Override
    public List<User> getUserList() {
        List<User> userList = null;
        Connection con = null;

        try {

            con = DatabaseUtil.getConnection();
            UserDao dao = new UserDaoImpl(con);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return userList;
    }

    @Override
    public User findUserLogin(String name) throws SQLException {
        Connection conn=null;
        conn = DatabaseUtil.getConnection();
        User user=null;
        UserDao dao=new UserDaoImpl(conn);
        user= dao.findUserLogin(name);
        return user;
    }

    @Override
    public CommonResult register(User user) {
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            UserDao userDao = new UserDaoImpl(con);
            User existUser = userDao.findUserLogin(user.getLoginName());
            if (null != existUser) {
                //用户已存在
                return new CommonResult().validateFailed("用户已存在");
            }
            userDao.insert(user);
        } catch (SQLException throwables) {
            return new CommonResult().validateFailed("服务器炸了");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return new CommonResult().success(null);
    }
}
