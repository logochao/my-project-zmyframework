/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-28 下午1:27:05
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-28   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.app.dao.contract.account;

import org.wendellup.core.dao.Dao;

import com.wendellup.app.valueobject.entity.Account;

/**
 * @author wendellup
 *
 */
public interface IAccountDao extends Dao<Account>{

	/**
	 * 获取某个用户名存在的个数 
	 * @param account
	 * @return
	 */
	int getNumByAccountName(Account account);

	/**
	 * 用户登录
	 * @param account
	 * @return
	 */
	Account login(Account account);

}
