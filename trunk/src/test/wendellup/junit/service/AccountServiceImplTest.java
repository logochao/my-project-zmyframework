/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     nanjingIT 
 * @author:     nanjingIT  
 * @version:    1.0  
 * Create at:   2013-1-25 上午10:04:29
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-25   nanjingIT     1.0       如果修改了;必填  
 */
package test.wendellup.junit.service;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.wendellup.core.support.Page;
import org.wendellup.core.support.QueryParams;

import com.wendellup.app.service.contract.account.IAccountService;
import com.wendellup.app.valueobject.entity.Account;
import com.wendellup.app.valueobject.entity.AccountCondition;
import com.wendellup.util.DatetimeUtils;

@SpringApplicationContext( { "applicationContext.xml" })
public class AccountServiceImplTest extends UnitilsJUnit4 {
	@SpringBean("iAccountService")
	private IAccountService iAccountService;

	// @Test
	public void queryAll() {
		// System.out.println(iMessageDao.queryAll().size());
		Assert.assertEquals(iAccountService.queryAll().size(), 3);
	}

	// @Test
	public void save() {
		Account account = new Account();
		account.setAccountName("ac03");
		account.setAccountPassword("pwd03");
		account.setAccountPicture("url03");
		account.setAccountCreateTime(new Date());
		Assert.assertEquals(Integer.class, iAccountService.save(account)
				.getClass());
	}

	// @Test
	public void delete() {
		Assert.assertEquals(new Integer(1), iAccountService.delete(20));
	}

	// @Test
	public void getById() {
		Assert.assertEquals("user01", iAccountService.getById(1)
				.getAccountName());
	}

	// @Test
	public void update() {
		Account account = new Account();
		account.setAccountName("NO.2");
		account.setId(1);
		Assert.assertEquals(new Integer(1), iAccountService.update(account));
	}

	// @Test
	public void queryByPage() {
		Page page = new Page();
		page.setCurrentPage(1);
		AccountCondition accountCondition = new AccountCondition();
		// accountCondition.setName("N");
		accountCondition.setStartTime(DatetimeUtils
				.parseDateByStr("2011-11-12 00:00:00"));
		// accountCondition.setEndTime(DatetimeUtils.parseDateByStr("2011-11-14 00:00:00"));
		QueryParams queryParams = new QueryParams();
		queryParams.setEntity(accountCondition);
		queryParams.setPaging(page);
		Assert.assertEquals(6, iAccountService.queryByPage(queryParams).size());
	}

	// @Test
	public void getNumByAccountName() {
		Account account = new Account();
		account.setAccountName("cc");
		Assert.assertEquals(15, iAccountService.getNumByAccountName(account));
	}

	@Test
	public void login() {
		Account account = new Account();
		account.setAccountName("lovex");
		account.setAccountPassword("lovex");
		Assert.assertEquals("lovex", iAccountService.login(account)
				.getAccountName());
	}
}
