package com.alimama.easybuy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	public static String getProperty(String keyString) {
		String value = "";
		Properties params = new Properties();
		String  configFile = "database.properties";
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try {
			params.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = params.getProperty(keyString);
		return value;
	}

	public static String getAlipayProperty(String keyString) {
		String value = "";
		Properties params = new Properties();
		String  configFile = "alipay.properties";
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try {
			params.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = params.getProperty(keyString);
		return value;
	}
}
