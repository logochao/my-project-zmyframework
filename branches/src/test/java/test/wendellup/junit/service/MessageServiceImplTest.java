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

import com.wendellup.app.service.contract.message.IMessageService;
import com.wendellup.app.valueobject.entity.Message;

@SpringApplicationContext({ "applicationContext.xml" })
public class MessageServiceImplTest extends UnitilsJUnit4 {
	@SpringBean("iMessageService")
	private IMessageService imessageService;

	// @Test
	public void queryAll() {
		// System.out.println(iMessageDao.queryAll().size());
		Assert.assertEquals(imessageService.queryAll().size(), 11);
	}

	// @Test
	public void save() {
		Message message = new Message();
		message.setAccountId(2);
		message.setMessageContent("hello world2");
		message.setMessageTime(new Date());
		Assert.assertEquals(Integer.class, imessageService.save(message)
				.getClass());
	}

	@Test
	public void queryAllWithAccountName() {
		// System.out.println(iMessageDao.queryAll().size());
		Assert.assertEquals("user01",imessageService.queryAllWithAccountName().get(0).getAccountName());
		Assert.assertEquals(4,imessageService.queryAllWithAccountName().size());
	}
}
