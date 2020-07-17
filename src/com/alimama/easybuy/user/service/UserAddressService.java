package com.alimama.easybuy.user.service;

import com.alimama.easybuy.user.bean.UserAddress;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jun Xiao
 * @date 2020/7/17 16:24
 */
public interface UserAddressService {

    //根据用户id获取所有地址
    List<UserAddress> getAddressByUserId(Integer uid);

    //添加地址
    Integer insertAddress(UserAddress userAddress);

    //修改地址
    Integer modifyAddress(UserAddress userAddress);

    //删除地址
    Integer delAddress(Integer id);

}
