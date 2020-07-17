package com.alimama.easybuy.user;

import com.alimama.easybuy.user.bean.UserAddress;
import com.alimama.easybuy.user.dao.UserAddressDao;
import com.alimama.easybuy.user.dao.impl.UserAddressDaoImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jun Xiao
 * @date 2020/7/17 13:28
 */
public class UserAddressTest {

    UserAddressDao userAddressDao;

    @Test
    public void f1() throws SQLException {
//        userAddressDao = new UserAddressDaoImpl(DBPool.getConnection());
//        List<UserAddress> list = userAddressDao.getAddressByUserId(2);
//
//        list.forEach(item -> {
//            System.out.println(item);
//        });
    }
}
