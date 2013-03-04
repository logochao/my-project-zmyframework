/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     nanjingIT 
 * @author:     nanjingIT  
 * @version:    1.0  
 * Create at:   2013-1-25 下午4:50:34
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-25   nanjingIT     1.0       如果修改了;必填  
 */
package com.wendellup.app.valueobject.bo.app;

import java.io.Serializable;

public class AppUserInfo implements Serializable {
	
	/**
	 * 
	 */
	private String id;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String ipaddress;
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
