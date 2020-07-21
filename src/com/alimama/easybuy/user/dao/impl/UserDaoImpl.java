package com.alimama.easybuy.user.dao.impl;

import com.alimama.easybuy.user.bean.User;
import com.alimama.easybuy.user.dao.UserDao;
import com.alimama.easybuy.util.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    public UserDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<User> getUserList() {

        return null;
    }

    // 获取用户登入
    @Override
    public User findUserLogin(String name) {
        User user=null;
        String sql="SELECT * FROM easybuy_user WHERE loginName=?";
        Object [] ob= {name};
        try {
            ResultSet resultSet = executeQuery(sql, ob);
            if (resultSet.next()){
                user = new User();
                user.setLoginName(resultSet.getString("loginName"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setSex(resultSet.getInt("sex"));
                user.setIdentityCode(resultSet.getString("identityCode"));
                user.setEmail(resultSet.getString("email"));
                user.setMobile(resultSet.getString("mobile"));
                user.setType(resultSet.getInt("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public Integer insert(User user) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `easybuy_user`(`loginName`,`password`,`userName`,`sex`");
        List params = new ArrayList();
        params.add(user.getLoginName());
        params.add(user.getPassword());
        params.add(user.getUserName());
        params.add(user.getSex());
        if (user.getIdentityCode() != "") {
            sql.append(",`identityCode`");
            params.add(user.getIdentityCode());
        }
        if (user.getEmail() != "") {
            sql.append(",`email`");
            params.add(user.getEmail());
        }
        if (user.getMobile() != "") {
            sql.append(",`mobile`");
            params.add(user.getMobile());
        }
        sql.append(")");
        sql.append(" Values(");
        for (int j = 0; j < params.size(); j++) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        Integer i = executeUpdate(sql.toString(),params.toArray());
        return i;
    }
}
