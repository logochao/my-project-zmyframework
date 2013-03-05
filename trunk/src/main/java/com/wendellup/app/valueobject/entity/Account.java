/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-28 下午1:22:48
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-28   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.app.valueobject.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.wendellup.core.dao.DomainObject;

/**
 * @author wendellup
 *
 */
public class Account extends DomainObject implements HttpSessionBindingListener{
    private Integer id;
    /**
     * 账户名
     */
    private String accountName;
    /**
     * 密码
     */
    private String accountPassword;
    /**
     * 照片路径
     */
    private String accountPicture;
    /**
     * 注册时间
     */
    private Date accountCreateTime;
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }
    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    /**
     * @return the accountPassword
     */
    public String getAccountPassword() {
        return accountPassword;
    }
    /**
     * @param accountPassword the accountPassword to set
     */
    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
    /**
     * @return the accountPicture
     */
    public String getAccountPicture() {
        return accountPicture;
    }
    /**
     * @param accountPicture the accountPicture to set
     */
    public void setAccountPicture(String accountPicture) {
        this.accountPicture = accountPicture;
    }
    /**
     * @return the accountCreateTime
     */
    public Date getAccountCreateTime() {
        return accountCreateTime;
    }
    /**
     * @param accountCreateTime the accountCreateTime to set
     */
    public void setAccountCreateTime(Date accountCreateTime) {
        this.accountCreateTime = accountCreateTime;
    }
    
    /**
     * {方法的功能/动作描述}
     * 
     * @param {引入参数名} {引入参数说明}
     * @return {返回参数名} {返回参数说明}
     * @exception {说明在某情况下,将发生什么异常}
     */
    @Override
    public String toString() {
    	return "accountName: "+accountName
    			+", accountPassword: "+accountPassword;
    }
	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		ServletContext sctx = event.getSession().getServletContext();
		List<Account> onlineAccountList = (List<Account>) sctx.getAttribute("onlineAccountList");
		if(onlineAccountList==null){
			//在线人数列表为null
			onlineAccountList = new ArrayList<Account>();
		}
		onlineAccountList.add(this);
		sctx.setAttribute("onlineAccountList", onlineAccountList);	
	}
	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		ServletContext sctx = event.getSession().getServletContext();
		List<Account> onlineAccountList = (List<Account>) sctx.getAttribute("onlineAccountList");
		if(onlineAccountList!=null){
			onlineAccountList.remove(this);
		}
	}
}
