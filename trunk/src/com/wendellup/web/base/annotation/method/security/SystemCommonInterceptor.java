package com.wendellup.web.base.annotation.method.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.log.LogUtils;
import com.wendellup.web.base.exceptions.PageNotFoundException;

/**  
 * Description: <类功能描述-必填> 系统常用拦截器，监控记录页面加载时间超过指定多少毫秒的页面
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup	 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-21 23:00:37
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-21   wendellup      1.0       如果修改了;必填  
 */
public class SystemCommonInterceptor implements HandlerInterceptor{
	private NamedThreadLocal<Long> privateThreadLocal = new NamedThreadLocal<Long>(
			"timeConsumes");
	//@Autowired
	//private LogUtils logUtils;
	private String moreThanTime = "100";
	private static final Logger logger = Logger.getLogger(SystemCommonInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long endTime = System.currentTimeMillis();
		long beginTime = privateThreadLocal.get();
		long comsumeTime = endTime - beginTime;
		List<String> ls = new ArrayList<String>();
		ls.add("RequestTimeStart--");
		ls.add(String.format("%s comsume %d millis", request.getRequestURI(),
				comsumeTime));
		ls.add("--RequestTimeEnd");
		if(comsumeTime>Long.parseLong(moreThanTime)){
			logger.info(ls);
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		try {
			long beginTime = System.currentTimeMillis();// 1、开始时间
			privateThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
			return true;// 继续流程
		} catch (Exception ex) {
			throw new PageNotFoundException("您请求的页面找不到!"+request.getRequestURL());
		}
	}

	public String getMoreThanTime() {
		return moreThanTime;
	}

	public void setMoreThanTime(String moreThanTime) {
		this.moreThanTime = moreThanTime;
	}

}
