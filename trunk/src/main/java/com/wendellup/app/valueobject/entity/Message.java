/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-25 上午9:15:19
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-25   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.app.valueobject.entity;

import java.util.Date;

import org.wendellup.core.dao.DomainObject;

public class Message extends DomainObject{
	private Integer id;
	/**
	 * 聊天人对应的id
	 */
	private Integer accountId;
	/**
	 * 聊天内容
	 */
	private String messageContent;
	/**
	 * 发送该聊天的时间
	 */
	private Date messageTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Date getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}
	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
}
