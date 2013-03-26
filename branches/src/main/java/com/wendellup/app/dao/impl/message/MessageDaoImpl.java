/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     nanjingIT 
 * @author:     nanjingIT  
 * @version:    1.0  
 * Create at:   2013-1-25 上午9:40:30
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-25   nanjingIT     1.0       如果修改了;必填  
 */
package com.wendellup.app.dao.impl.message;

import org.springframework.stereotype.Repository;
import org.wendellup.core.dao.DefaultDaoImpl;

import com.wendellup.app.dao.contract.message.IMessageDao;
import com.wendellup.app.valueobject.entity.Message;

@Repository("iMessageDao")
public class MessageDaoImpl extends DefaultDaoImpl<Message> 
	implements IMessageDao{
	
}
