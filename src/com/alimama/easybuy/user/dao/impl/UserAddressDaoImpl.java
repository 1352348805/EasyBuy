package com.alimama.easybuy.user.dao.impl;

import com.alimama.easybuy.user.bean.UserAddress;
import com.alimama.easybuy.user.dao.UserAddressDao;
import com.alimama.easybuy.util.BaseDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jun Xiao
 * @date 2020/7/17 13:18
 */
public class UserAddressDaoImpl extends BaseDao implements UserAddressDao {

    private Logger logger = Logger.getLogger(UserAddressDaoImpl.class);

    public UserAddressDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<UserAddress> getAddressByUserId(Integer uid) {
        List<UserAddress> userAddressList = new ArrayList<>();
        String sql = "SELECT * FROM `easybuy_user_address` WHERE `userId` = ?";
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery(sql, uid);
            while (resultSet.next()) {
                UserAddress userAddress = new UserAddress();
                userAddress.setId(resultSet.getInt("id"));
                userAddress.setUserId(resultSet.getInt("userId"));
                userAddress.setAddress(resultSet.getString("address"));
                userAddress.setCreateTime(resultSet.getDate("createTime"));
                userAddress.setIsDefault(resultSet.getInt("isDefault"));
                userAddress.setRemark(resultSet.getString("remark"));
                userAddressList.add(userAddress);
            }
        } catch (SQLException throwables) {
            logger.error("SQL执行失败!请检查sql语句或参数...");
            throwables.printStackTrace();
        }
        return userAddressList;
    }

    @Override
    public Integer insertAddress(UserAddress userAddress) {
        Integer i = 0;
        String sql = "INSERT INTO `easybuy_user_address`(`userId`,`address`,`createTime`,`remark`) VALUES(?,?,?,?)";
        List list = new ArrayList();
        list.add(userAddress.getUserId());
        list.add(userAddress.getAddress());
        list.add(userAddress.getCreateTime());
        list.add(userAddress.getRemark());
        try {
            i = executeUpdate(sql,list.toArray());
        } catch (SQLException throwables) {
            logger.error("SQL执行失败!请检查sql语句或参数...");
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public Integer modifyAddress(UserAddress userAddress) {
        Integer i = 0;
        List params = new ArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE `easybuy_user_address` SET");
        sql.append(" `address` = ?");
        params.add(userAddress.getAddress());
        sql.append(" ,`isDefault` = ?");
        params.add(userAddress.getIsDefault());
        sql.append(" ,`remark` = ?");
        params.add(userAddress.getRemark());
        sql.append(" WHERE id = ?");
        params.add(userAddress.getId());
        try {
            i = executeUpdate(sql.toString(),params.toArray());
        } catch (SQLException throwables) {
            logger.error("SQL执行失败!请检查sql语句或参数...");
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public Integer delAddress(Integer id) {
        Integer i = 0;
        String sql = "DELETE FROM `easybuy_user_address` WHERE id = ?";
        try {
            i = executeUpdate(sql,id);
        } catch (SQLException throwables) {
            logger.error("SQL执行失败!请检查sql语句或参数...");
            throwables.printStackTrace();
        }
        return i;
    }
}
