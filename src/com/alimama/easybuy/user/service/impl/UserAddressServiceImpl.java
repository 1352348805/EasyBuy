package com.alimama.easybuy.user.service.impl;

import com.alimama.easybuy.user.bean.UserAddress;
import com.alimama.easybuy.user.dao.UserAddressDao;
import com.alimama.easybuy.user.dao.impl.UserAddressDaoImpl;
import com.alimama.easybuy.user.service.UserAddressService;
import com.alimama.easybuy.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author asuk
 * @date 2020/7/17 16:22
 */
public class UserAddressServiceImpl implements UserAddressService {


    @Override
    public List<UserAddress> getAddressByUserId(Integer uid) {
        List<UserAddress> userAddressList = null;
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(con);
            userAddressList = userAddressDao.getAddressByUserId(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return userAddressList;
    }

    @Override
    public Integer insertAddress(UserAddress userAddress) {
        Integer i = 0;
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(con);
            i = userAddressDao.insertAddress(userAddress);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public Integer modifyAddress(UserAddress userAddress) {
        Integer i = 0;
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(con);
            i = userAddressDao.modifyAddress(userAddress);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public Integer delAddress(Integer id) {
        Integer i = 0;
        Connection con = null;
        try {
            con = DatabaseUtil.getConnection();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(con);
            i = userAddressDao.delAddress(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return i;
    }

}
