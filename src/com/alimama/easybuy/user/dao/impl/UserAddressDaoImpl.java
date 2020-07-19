package com.alimama.easybuy.user.dao.impl;

import com.alimama.easybuy.user.bean.UserAddress;
import com.alimama.easybuy.user.dao.UserAddressDao;
import com.alimama.easybuy.util.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/17 13:18
 */
public class UserAddressDaoImpl extends BaseDao implements UserAddressDao {

    public UserAddressDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<UserAddress> getAddressByUserId(Integer uid) throws SQLException {
        List<UserAddress> userAddressList = new ArrayList<>();
        String sql = "SELECT * FROM `easybuy_user_address` WHERE `userId` = ?";
        ResultSet resultSet = executeQuery(sql, uid);
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
        return userAddressList;
    }

    @Override
    public Integer insertAddress(UserAddress userAddress) throws SQLException {
        String sql = "INSERT INTO `easybuy_user_address`(`userId`,`address`,`createTime`,`remark`) VALUES(?,?,?,?)";
        Integer i = executeUpdate(sql,
                userAddress.getUserId(),
                userAddress.getAddress(),
                userAddress.getCreateTime(),
                userAddress.getRemark());
        return i;
    }

    @Override
    public Integer modifyAddress(UserAddress userAddress) throws SQLException {
        String sql = "UPDATE `easybuy_user_address` SET `address` = ?,`isDefault` = ?,`remark` = ? WHERE id = ?";
        Integer i = executeUpdate(sql.toString(),
                userAddress.getAddress(),
                userAddress.getIsDefault(),
                userAddress.getRemark(),
                userAddress.getId());
        return i;
    }

    @Override
    public Integer delAddress(Integer id) throws SQLException {
        String sql = "DELETE FROM `easybuy_user_address` WHERE id = ?";
        Integer i = executeUpdate(sql,id);
        return i;
    }
}
