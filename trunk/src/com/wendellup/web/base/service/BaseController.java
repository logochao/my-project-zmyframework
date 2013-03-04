/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-25 下午4:16:38
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-25   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.web.base.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;
import org.wendellup.core.configuration.ConfigurationUtils;
import org.wendellup.core.web.servlet.WendellupDispatcherServlet;

import com.wendellup.app.valueobject.bo.app.AppUserInfo;
import com.wendellup.app.valueobject.constants.AppConstants;

public abstract class BaseController extends MultiActionController {
	/**
	 * 日志通用
	 */
	private static final Logger log = Logger.getLogger(BaseController.class);

	protected static final String ERROR_MSG_KEY = "errorMsg";

	/**
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 添加一个日期类型编辑器，也就是需要日期类型的时候，怎么把字符串转化为日期类型
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * 提高ModelAndView性能，减少垃圾回收的性能损耗 ModelAndView利用池 (可选)
	 */
	public ModelAndView getModeAndView() {
		return WendellupDispatcherServlet.getModeAndView();
	}

	/**
	 * 向View层传递message时将message放入httpSession的messages变量中. *
	 * 放在session中能保证message即使Redirect也不会消失。 需配合
	 * {@link com.bidlink.core.web.MessageFilter MessageFilter}使用
	 * 
	 * @param request
	 * @param message
	 */
	protected void saveMessage(HttpServletRequest request, String message) {
		if (StringUtils.isNotBlank(message)) {
			List messages = (List) WebUtils.getOrCreateSessionAttribute(
					request.getSession(), "messages", ArrayList.class);
			messages.add(message);
		}
	}

	/**
	 * 直接输出纯字符串 〈功能详细描述〉
	 * 
	 * @param response
	 * @param content
	 */
	public void renderText(HttpServletResponse response, String content) {
		try { // response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 直接输出纯HTML 〈功能详细描述〉
	 * 
	 * @param response
	 * @param content
	 */
	public void renderHtml(HttpServletResponse response, String content) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 直接输出纯XML 〈功能详细描述〉
	 * 
	 * @param response
	 * @param content
	 */
	public void renderXML(HttpServletResponse response, String content) {
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 直接输出json 〈功能详细描述〉
	 * 
	 * @param response
	 * @param content
	 */
	public void renderJson(HttpServletResponse response, String content) {
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 〈功能详细描述〉
	 * 
	 * @param msgKey
	 * @return
	 */
	public String getText(String msgKey) {
		return getMessageSourceAccessor().getMessage(msgKey);
	}

	/**
	 * 〈功能详细描述〉
	 * 
	 * @param msgKey
	 * @param arg
	 * @return
	 */
	public String getText(String msgKey, String arg) {
		return getText(msgKey, new Object[] { arg });
	}

	/**
	 * 〈功能详细描述〉
	 * 
	 * @param msgKey
	 * @param args
	 * @return
	 */
	public String getText(String msgKey, Object[] args) {
		return getMessageSourceAccessor().getMessage(msgKey, args);
	}

	/**
	 * 1. 获取保存在Session中的用户对象 〈功能详细描述〉
	 * 
	 * @param request
	 * @return
	 */
	protected AppUserInfo getSessionUser(HttpServletRequest request) {
		return (AppUserInfo) request.getSession().getAttribute(
				AppConstants.CURRENT_USER);
	}

	/**
	 * 将用户对象保存到Session中 〈功能详细描述〉
	 * 
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(HttpServletRequest request, AppUserInfo user) {
		request.getSession().setAttribute(AppConstants.CURRENT_USER, user);
	}

	/**
	 * 获取基于应用程序的url绝对路径 〈功能详细描述〉
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}

	/**
	 * 自动获得返回路径的地址，页面一般在模块的view的pages路径下+方法名称+.jsp 〈功能详细描述〉
	 * 
	 * @param methodName
	 *            替换为自定义页面的名称，不要加后缀".jsp"
	 * @param isReplace
	 *            true:替换 false:不要替换
	 * @return 指定的页面
	 * @exception Exception
	 * @Author wendellup
	 */
	protected String AutoGetURL() {
		// String methodName, boolean isReplace
		try {
			ConfigurationUtils.init("moduleUrlMapping.properties");
			String[] _ControllerNameArr = StringUtils.split(
					new Exception().getStackTrace()[1].getClassName(), ".");
			String _ControllerName = _ControllerNameArr[_ControllerNameArr.length - 1];
			String ModuleName = ConfigurationUtils.getString(_ControllerName
					.toLowerCase().replace("controller", ""));
			/*
			 * if (isReplace) { return ModuleName + methodName; } else {
			 */
			return ModuleName
					+ new Exception().getStackTrace()[1].getMethodName();
			// }

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
}
