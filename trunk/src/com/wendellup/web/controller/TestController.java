package com.wendellup.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.wendellup.core.configuration.ConfigurationUtils;

import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.service.BaseController;

@Controller
@RequestMapping("/test")
//@NeedLogin
public class TestController extends BaseController{
    static{
        try {
            ConfigurationUtils.init("redirectUrl.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
    
	private static final String MODULEDESC = "访问模块";

	private final static String CHATROOM = "chatroom";
	private final static String LOGOUT = "logout";
	
	/**
	 * 跳转到的页面从配置文件中读取,避免硬编码
	 */
	private final static String RedirectToLogin = ConfigurationUtils.getString("redirectToLogin");
	
	@BusinessDesc(MethodDesc="登陆成功后显示的主界面",ModuleDesc=MODULEDESC)
	@RequestMapping(value = CHATROOM)
	public String chatroom() throws Exception {
	    return this.AutoGetURL();
	}

	@BusinessDesc(MethodDesc="登出",ModuleDesc=MODULEDESC)
	@RequestMapping(value = LOGOUT)
	public ModelAndView logout(HttpServletRequest request) throws Exception {
//		Person loginPerson = (Person) request.getSession().getAttribute("loginPerson");
//		((List<Person>) request.getSession().getServletContext().getAttribute("onlinePersonList")).remove(loginPerson);
//		request.getSession().removeAttribute("loginPerson");
	    request.getSession().invalidate();
		ModelAndView mav = new ModelAndView(new RedirectView(RedirectToLogin));
		return mav;
	}

	public String onlinePersonList(HttpServletRequest request) throws Exception {
		return "redirect:common/conlinePersonList";
	}

}
