/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-25 下午2:59:32
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-25   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.app.service.contract.message;

import java.util.List;

import org.wendellup.core.service.Service;

import com.wendellup.app.valueobject.entity.Message;
import com.wendellup.app.valueobject.vo.MessageVo;

public interface IMessageService extends Service<Message>{
	List<MessageVo> queryAllWithAccountName();
}
