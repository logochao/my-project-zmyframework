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
package com.wendellup.app.valueobject.vo;

import java.util.Date;

import javax.validation.constraints.Size;

import org.wendellup.core.dao.DomainObject;

import com.wendellup.app.valueobject.constraint.PasswordMatches;

/**
 * @author wendellup
 *
 */
@PasswordMatches(field = "accountPassword", verifyField = "confirmPassword",  
		message = "两次密码不一致") 
public class AccountVo extends DomainObject{
    private Integer id;
    
    @Size(min=3,max=10,message="账户名长度为3到5位")
    private String accountName;
    
    @Size(min=3,max=10,message="密码长度为3到10位")
    private String accountPassword;
    
    private String confirmPassword;
    
    private String accountPicture;
    private Date accountCreateTime;
    
    //验证码
    private String validateCode;
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
	 * @return the validateCode
	 */
	public String getValidateCode() {
		return validateCode;
	}
	/**
	 * @param validateCode the validateCode to set
	 */
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
}
