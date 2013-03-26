/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     yuchao  
 * @version:    1.0  
 * Create at:   2013-1-28 下午1:28:25
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-28   yuchao     1.0       如果修改了;必填  
 */
package com.wendellup.app.dao.impl.account;

import org.springframework.stereotype.Repository;
import org.wendellup.core.dao.DefaultDaoImpl;

import com.wendellup.app.dao.contract.account.IAccountDao;
import com.wendellup.app.valueobject.entity.Account;

/**
 * @author wendellup
 *
 */
@Repository("iAccountDao")
public class AccountDaoImpl extends DefaultDaoImpl<Account> implements IAccountDao{

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public int getNumByAccountName(Account account) {
		return (Integer) this.getReadTemplate().queryForObject(getStatement(), account);
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Account login(Account account) {
		return (Account) this.getReadTemplate().queryForObject(getStatement(), account);
	}

	
}
