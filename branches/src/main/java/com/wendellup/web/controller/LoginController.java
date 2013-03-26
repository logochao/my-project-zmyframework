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
import javax.validation.Valid;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.wendellup.core.configuration.ConfigurationUtils;

import com.wendellup.app.service.contract.account.IAccountService;
import com.wendellup.app.valueobject.entity.Account;
import com.wendellup.app.valueobject.vo.AccountVo;
import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.exceptions.BusinessException;
import com.wendellup.web.base.exceptions.ServiceException;
import com.wendellup.web.base.service.BaseController;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@Autowired
	@Qualifier("iAccountService")
	private IAccountService iAccountService;
	
	static {
		try {
			ConfigurationUtils.init("redirectUrl.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private final static String MODULE_DESC = "登录模块";
	private final static String LOGIN_EXIT = "exit";
	
	//登陆后跳转到主页面
	private final static String INDEX = ConfigurationUtils
	.getString("RedirectToIndex"); 
	
	//退出后跳转到登录界面,跳转到的页面从配置文件中读取,避免硬编码
	private final static String LOGIN = ConfigurationUtils
			.getString("RedirectToLogin");
	
	/**
	 * 显示登录页面
	 */
	@BusinessDesc(MethodDesc = "用户登录页面", ModuleDesc = MODULE_DESC)
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request, @ModelAttribute("accountVo") AccountVo accountVo)
			throws Exception {
		String redirectURL = request.getParameter("redirectURL");
		if (redirectURL != null) {
			request.setAttribute("redirectURL", redirectURL);
		}
		return this.AutoGetURL();
	}
	
	/**
	 * 用户登录
	 */
	@SuppressWarnings("all")
	@BusinessDesc(ModuleDesc = MODULE_DESC, MethodDesc = "填写用户名密码")
	@RequestMapping(method = RequestMethod.POST )
	public ModelAndView login(
			HttpServletRequest request,
			@ModelAttribute("accountVo") AccountVo accountVo,
			BindingResult result)
			throws Exception {
		try {
			Account account = new Account();
			account.setAccountName(accountVo.getAccountName());
			account.setAccountPassword(accountVo.getAccountPassword());

			Account retAccount = null;
			
			// 判断登录的用户是否存在
			int num = iAccountService.getNumByAccountName(account);
			if (num == 0) {
				result.addError(new FieldError("accountVo", "accountName",
						"该用户名不存在"));
			}else{
				// 判断登录的用户的密码是否正确
				retAccount = iAccountService.login(account);
				if (retAccount == null) {
					result.addError(new FieldError("accountVo", "accountPassword",
							"密码错误"));
				}else{
					/** 验证码进行验证 */
					String kaptchaExpected = (String) request
							.getSession()
							.getAttribute(
									com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
					if(!accountVo.getValidateCode().equals(kaptchaExpected)){
						result.addError(new FieldError("accountVo", "validateCode",
								"验证码错误"));
					}
				}
			}
			
			if (result.hasErrors()) {
				// 后台验证不通过
				return new ModelAndView(AutoGetURL());
			}

			//登录成功的用户信息保存在session中
			request.getSession().setAttribute("sessionAccount", retAccount);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
		String redirectURL = request.getParameter("redirectURL");
		if(redirectURL != null){
			return new ModelAndView(new RedirectView(redirectURL));
		}
		return new ModelAndView(new RedirectView(INDEX));
	}

	/**
	 * 退出登录后跳至登录页面 
	 */
	@BusinessDesc(ModuleDesc = MODULE_DESC, MethodDesc = "退出登录")
	@RequestMapping(value = LOGIN_EXIT, method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView exit(HttpServletRequest request,
			HttpServletResponse response) {
//		request.getSession().setAttribute("sessionKey", null);
		request.getSession().setAttribute("sessionAccount", null);
		return new ModelAndView(new RedirectView(LOGIN));
	}
	

}
