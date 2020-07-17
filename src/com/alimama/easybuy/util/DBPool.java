package com.alimama.easybuy.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接池
 * @author Jun Xiao
 * @date 2020/7/15 12:07
 */
public class DBPool {

    static Logger log = Logger.getLogger(DBPool.class);
    private static DruidDataSource dataSource = null;

    static {
        try {
            Properties params = new Properties();
            String  configFile = "database.properties";
            InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
            params.load(is);
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(params);
        } catch (Exception e) {
            log.error("获取配置失败");
        }
    }

    /**
     * 数据库连接池单例
     * @return
     */
//    public static synchronized DBPool getInstance(){
//        if (null == dbPoolConnection){
//            dbPoolConnection = new DBPool();
//        }
//        return dbPoolConnection;
//    }

    private DBPool(){}

    /**
     * 返回druid数据库连接
     * @return
     * @throws SQLException
     */
    public static DruidPooledConnection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
