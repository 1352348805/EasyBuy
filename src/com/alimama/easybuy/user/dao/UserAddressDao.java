package com.alimama.easybuy.user.dao;

import com.alimama.easybuy.user.bean.UserAddress;

import java.sql.SQLException;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/17 13:07
 */
public interface UserAddressDao {

    //根据用户id获取所有地址
    List<UserAddress> getAddressByUserId(Integer uid) throws SQLException;

    //添加地址
    Integer insertAddress(UserAddress userAddress) throws SQLException;

    //修改地址
    Integer modifyAddress(UserAddress userAddress) throws SQLException;

    //删除地址
    Integer delAddress(Integer id) throws SQLException;
}
