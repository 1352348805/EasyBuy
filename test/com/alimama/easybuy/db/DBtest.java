package com.alimama.easybuy.db;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.alimama.easybuy.util.DBPool;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jun Xiao
 * @date 2020/7/15 12:09
 */
public class DBtest {


    @Test
    public void dbPool() throws SQLException {
        for (int i = 0; i < 19 ; i++) {
            DruidPooledConnection con = DBPool.getConnection();
            System.out.println(con);
        }

    }
}
