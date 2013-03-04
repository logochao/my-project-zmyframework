/**  
 * Description: <类功能描述-必填>  用户注册模块
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-2-1 下午5:06:32
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-2-1   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wendellup.app.service.contract.account.IAccountService;
import com.wendellup.app.valueobject.entity.Account;
import com.wendellup.app.valueobject.vo.AccountVo;
import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.exceptions.ServiceException;
import com.wendellup.web.base.service.BaseController;

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {
	@Autowired
	@Qualifier("iAccountService")
	private IAccountService iAccountService;

	private static final String MODULE_DESC = "用户注册模块";

	private final static String REGISTER = "register";

	private final static String ACCOUNT_NAME_VERIFY = "accountNameVerify";

	private static Logger logger = Logger.getLogger(RegisterController.class);

	/** 注册成功跳转到主页 */
	private final static String INDEX = "index";

	@BusinessDesc(MethodDesc = "用户注册页面", ModuleDesc = MODULE_DESC)
	@RequestMapping(value = REGISTER, method = RequestMethod.GET)
	public String register(@ModelAttribute("accountVo") AccountVo accountVo)
			throws Exception {
		return this.AutoGetURL();
	}

	@BusinessDesc(MethodDesc = "用户注册", ModuleDesc = MODULE_DESC)
	@RequestMapping(value = REGISTER, method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,
			@ModelAttribute("accountVo") @Valid AccountVo accountVo,
			BindingResult result) throws ServiceException {
		try {
			// List<String> lsError = new ArrayList<String>();
			// if (result.hasErrors()) {
			// // List<ObjectError> lsObjectError = result.getAllErrors();
			// List<FieldError> lsFieldError = result.getFieldErrors();
			// for (FieldError fe : lsFieldError) {
			// logger.info(fe.getField()+", "+fe.getDefaultMessage());
			// }
			// }
			// for (String err : lsError) {
			// logger.info(err);
			// }

			Account account = new Account();
			account.setAccountName(accountVo.getAccountName());
			account.setAccountPassword(accountVo.getAccountPassword());

			request.getSession().removeAttribute("errors");
			// 判断用户名是否已经存在
			int num = iAccountService.getNumByAccountName(account);
			if (num != 0) {
				result.addError(new FieldError("accountVo", "accountName",
						"用户名已存在"));
			}

			/** 验证码进行验证 */
			String kaptchaExpected = (String) request
					.getSession()
					.getAttribute(
							com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if(!accountVo.getValidateCode().equals(kaptchaExpected)){
				result.addError(new FieldError("accountVo", "validateCode",
						"验证码错误"));
			}
			
			if (result.hasErrors()) {
				// 后台验证不通过
				return new ModelAndView(AutoGetURL());
			}

			// 验证通过,将注册的信息插入数据库
			int key = iAccountService.save(account);
			if (key < 0) {
				throw new ServiceException("插入数据失败");
			}
			//将注册成功(登录)的用户信息保存在session中
			account.setId(key);
			request.getSession().setAttribute("sessionAccount", account);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return new ModelAndView(new RedirectView(INDEX));
	}

	/**
	 * ajax验证name是否存在
	 */
	@BusinessDesc(ModuleDesc = MODULE_DESC, MethodDesc = "ajax验证用户名是否存在")
	@RequestMapping(value = ACCOUNT_NAME_VERIFY, method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> accountNameVerify(String accountName) {
		Account account = new Account();
		account.setAccountName(accountName);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String error = "用户名已存在";
			int num = iAccountService.getNumByAccountName(account);
			map.put("num", num);
			map.put("errorInfo", error);
		} catch (Exception e) {
			map.put("error", e.getMessage());
		}

		return map;
	}

}
