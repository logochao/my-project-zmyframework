/**  
 * Description: <类功能描述-必填> 
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2012-12-21 下午4:22:51  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2012-12-21   wendellup      1.0       如果修改了;必填  
 */
package com.wendellup.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wendellup.app.valueobject.constants.AppConstants;
import com.wendellup.util.LogUtils;
import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.exceptions.BusinessException;
import com.wendellup.web.base.service.BaseController;


/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author chenzhao
 * @version [版本号, 2012-12-17]
 */
@Controller
@RequestMapping("/login")
//@SessionAttributes(AppConstants.USER_INFO_SESSION)
public class LoginController extends BaseController {
	/**
	 * 登录模块
	 */
	private final static String MODULE_DES = "登录模块";
	/**
	 * exit
	 */
	private final static String LOGIN_EXIT = "exit";

	/**
	 * 〈功能详细描述〉
	 * 
	 * @param response
	 * @param request
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "填写用户名密码")
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String login(
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "redirectURL", required = false) String redirectURL)
			throws Exception {
		try {
			if (redirectURL != null) {
				request.getSession().setAttribute("redirectURL", redirectURL);
			}
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return this.AutoGetURL();
	}

	/**
	 * 退出登录后调至登录页面 〈功能详细描述〉
	 * 
	 * @param request
	 * @param response
	 * @return

	
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "退出登录")
	@RequestMapping(value = LOGIN_EXIT, method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView exit(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().setAttribute("sessionKey", null);
		return new ModelAndView(new RedirectView("/login"));
	}
	

}
