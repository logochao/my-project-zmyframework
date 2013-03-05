package com.wendellup.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wendellup.core.configuration.ConfigurationUtils;

import com.wendellup.app.service.contract.message.IMessageService;
import com.wendellup.app.valueobject.entity.Account;
import com.wendellup.app.valueobject.entity.Message;
import com.wendellup.app.valueobject.vo.MessageVo;
import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.annotation.bind.NeedLogin;
import com.wendellup.web.base.service.BaseController;

@Controller
@RequestMapping("/chatroom")
@NeedLogin
public class ChatroomController extends BaseController{
	@Autowired
	@Qualifier("iMessageService")
	private IMessageService iMessageService;
	
    static{
        try {
            ConfigurationUtils.init("redirectUrl.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
    
	private static final String MODULE_DESC = "聊天室模块";
	
	private final static String CHATROOM = "chatroom";
	private final static String MESSAGE_CONTENT = "messageContent";
	
	private final static String SEND_MESSAGE = "sendMessage";
	
	
	/**
	 * 跳转到的页面从配置文件中读取,避免硬编码
	 */
	private final static String RedirectToLogin = ConfigurationUtils.getString("redirectToLogin");
	
	@BusinessDesc(MethodDesc="登陆成功后显示的主界面",ModuleDesc=MODULE_DESC)
	@RequestMapping(method = RequestMethod.GET)
	public String chatroom() throws Exception {
	    return this.AutoGetURL();
	}
	
	@BusinessDesc(MethodDesc="显示所有聊天信息",ModuleDesc=MODULE_DESC)
	@RequestMapping(value = MESSAGE_CONTENT,method = RequestMethod.GET)
	public String messageContent(HttpServletRequest request) throws Exception {
		List<MessageVo> messageVoList = iMessageService.queryAllWithAccountName();
		request.setAttribute("messageVoList", messageVoList);
	    return this.AutoGetURL();
	}
	
	@BusinessDesc(MethodDesc="显示所有聊天信息",ModuleDesc=MODULE_DESC)
	@RequestMapping(value = SEND_MESSAGE,method = RequestMethod.POST)
	public void sendMessage(HttpServletRequest request,String str) throws Exception {
		Message message = new Message();
		Account account = (Account) request.getSession().getAttribute("sessionAccount");
		message.setAccountId(account.getId());
		message.setMessageContent(str);
		iMessageService.save(message);
	}
}
