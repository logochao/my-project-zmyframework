/**  
 * Description: 账户查询对应的实体类
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-29 下午5:36:43
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-29   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.app.valueobject.entity;

import java.util.Date;


public class AccountCondition {
	
	public AccountCondition(){
		
	}
	public AccountCondition(String name, Date startTime, Date endTime) {
		super();
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	//账户名
	private String name;
	//注册起始时间
	private Date startTime;
	//注册结束时间
	private Date endTime;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
