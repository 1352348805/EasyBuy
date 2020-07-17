package com.alimama.easybuy.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.log4j.Logger;

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
    private static final String driver = ConfigManager.getProperty("driver");
    private static final String url = ConfigManager.getProperty("url");
    private static final String username = ConfigManager.getProperty("username");
    private static final String password = ConfigManager.getProperty("password");
    private static final String initialSize = ConfigManager.getProperty("initialSize");
    private static final String minIdle = ConfigManager.getProperty("minIdle");
    private static final String maxActive = ConfigManager.getProperty("maxActive");
    private static final String maxWait = ConfigManager.getProperty("maxWait");
    private static final String timeBetweenEvictionRunsMillis = ConfigManager.getProperty("timeBetweenEvictionRunsMillis");
    private static final String minEvictableIdleTimeMillis = ConfigManager.getProperty("minEvictableIdleTimeMillis");
    private static final String validationQuery = ConfigManager.getProperty("validationQuery");
    private static final String testWhileIdle = ConfigManager.getProperty("testWhileIdle");
    private static final String testOnBorrow = ConfigManager.getProperty("testOnBorrow");
    private static final String testOnReturn = ConfigManager.getProperty("testOnReturn");
    private static final String poolPreparedStatements = ConfigManager.getProperty("poolPreparedStatements");
    private static final String maxPoolPreparedStatementPerConnectionSize = ConfigManager.getProperty("maxPoolPreparedStatementPerConnectionSize");
    private static final String removeAbandoned = ConfigManager.getProperty("removeAbandoned");
    private static final String removeAbandonedTimeout = ConfigManager.getProperty("removeAbandonedTimeout");
    private static final String logAbandoned = ConfigManager.getProperty("logAbandoned");
    private static final String filters = ConfigManager.getProperty("filters");
    static {
        try {
            DruidDataSource ds = new DruidDataSource();
            ds.setDriverClassName(driver);
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);
            ds.setMaxActive(Integer.parseInt(maxActive));
            ds.setInitialSize(Integer.parseInt(initialSize));
            ds.setMinIdle(Integer.parseInt(minIdle));
            ds.setMaxWait(Integer.parseInt(maxWait));
            ds.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis));
            ds.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis));
            ds.setValidationQuery(validationQuery);
            ds.setTestWhileIdle(new Boolean(testWhileIdle));
            ds.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(maxPoolPreparedStatementPerConnectionSize));
            dataSource = ds;
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


    /**
     * 返回druid数据库连接
     * @return
     * @throws SQLException
     */
    public static DruidPooledConnection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
