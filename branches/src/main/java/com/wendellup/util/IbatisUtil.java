package com.wendellup.util;

import java.io.InputStream;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IbatisUtil {
	private static SqlMapClient client;
	static{
		InputStream inputStream = null;
		try{
			inputStream = Resources.class.getClassLoader().getResourceAsStream("ibatis/SqlMapConfig.xml");
		}catch (Exception e) {
			e.printStackTrace();
		}
		client = SqlMapClientBuilder.buildSqlMapClient(inputStream);
	}
	
	public static SqlMapClient getClient(){
		return client;
	}
	
	public static void main(String[] args) {
		System.out.println(IbatisUtil.getClient());
	}
}
