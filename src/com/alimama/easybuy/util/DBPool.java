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
    private static DBPool dbPoolConnection = null;
    private static DruidDataSource druidDataSource = null;

    static {
        Properties properties = loadPropertiesFile("database.properties");
        try {
            druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties); //DruidDataSrouce工厂模式
        } catch (Exception e) {
            log.error("获取配置失败");
        }
    }

    /**
     * 数据库连接池单例
     * @return
     */
    public static synchronized DBPool getInstance(){
        if (null == dbPoolConnection){
            dbPoolConnection = new DBPool();
        }
        return dbPoolConnection;
    }

    private DBPool() {}

    /**
     * 返回druid数据库连接
     * @return
     * @throws SQLException
     */
    public static DruidPooledConnection getConnection() throws SQLException {
        return druidDataSource.getConnection();
    }
    /**
     * @param fullFile 配置文件名
     * @return Properties对象
     */
    private static Properties loadPropertiesFile(String fullFile) {
        String webRootPath = null;
        if (null == fullFile || fullFile.equals("")){
            throw new IllegalArgumentException("Properties file path can not be null" + fullFile);
        }
        webRootPath = DBPool.class.getClassLoader().getResource(fullFile).getPath();
        webRootPath = new File(webRootPath).getParent();
        InputStream inputStream = null;
        Properties p =null;
        try {
            inputStream = new FileInputStream(new File(webRootPath + File.separator + fullFile));
            p = new Properties();
            p.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream){
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return p;
    }
}
