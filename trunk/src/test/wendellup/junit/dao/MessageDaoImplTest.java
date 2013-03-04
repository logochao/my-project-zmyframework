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
package test.wendellup.junit.dao;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.wendellup.app.dao.contract.message.IMessageDao;
import com.wendellup.app.valueobject.entity.Message;

@SpringApplicationContext({ "applicationContext.xml" })
public class MessageDaoImplTest extends UnitilsJUnit4{
	@SpringBean("iMessageDao")
	private IMessageDao iMessageDao;
	
//	@Before
//	public void init(){
//		new ClassPathXmlApplicationContext(new String[] {
//				"applicationContext.xml","applicationContext-datasource.xml"});
//	}
	
	//@Test
	public void queryAll(){
		//System.out.println(iMessageDao.queryAll().size());
		Assert.assertEquals(iMessageDao.queryAll().size(), 2);
	}
	
	@Test
	public void save(){
		Message message = new Message();
		message.setAccountId(1);
		message.setMessageContent("hello world");
		message.setMessageTime(new Date());
		Assert.assertEquals(Integer.class, iMessageDao.save(message).getClass());
	}
}
