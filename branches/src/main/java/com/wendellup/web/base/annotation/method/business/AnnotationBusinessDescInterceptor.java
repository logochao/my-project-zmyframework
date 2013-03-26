/**  
 * Description: <类功能描述-必填> 拦截业务日志信息
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-22 上午11:34:16
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-22   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.web.base.annotation.method.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wendellup.util.CookieUtils;
import com.wendellup.util.DatetimeUtils;
import com.wendellup.util.LogUtils;
import com.wendellup.web.base.annotation.bind.BusinessDesc;

public class AnnotationBusinessDescInterceptor extends HandlerInterceptorAdapter{
	
    /**
     * 日志公共类
     */
	@Resource
	private LogUtils logUtils;
	
	/* (non-Javadoc)  
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)  
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		List<String> lsLog = new ArrayList<String>();
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		HandlerMethod hd = (HandlerMethod)handler;
		if(hd.getMethod().isAnnotationPresent(BusinessDesc.class)){
			BusinessDesc businessDesc = hd.getMethod().getAnnotation(BusinessDesc.class);
			String getRequestURL = request.getRequestURL().toString();
			String getSession = request.getSession().getId().toString();
			String getRemoteAddr = request.getRemoteAddr();
			String getMethod = request.getMethod();
			String getQueryString = request.getQueryString();
			String getRemoteHost = request.getRemoteHost();
			
			String getCookieUserName = CookieUtils.getCookie(request, response,"cookieKey");
			lsLog.add("***********[BussinessLog]AnnotationBusinessDescInterceptor***********");
			lsLog.add("Request Time=["
					+DatetimeUtils.getCurDate("yyyy-MM-dd_HH:mm:ss")+"]");
			lsLog.add("GetModule:"+businessDesc.ModuleDesc());
			lsLog.add("GetMethodName:"+businessDesc.MethodDesc());
			lsLog.add("GetRemoteAddr:"+getRemoteAddr);
			lsLog.add("GetSession:"+getSession);
			lsLog.add("GetRequestURL:"+getRequestURL);
			lsLog.add("GetMethod:"+getMethod);
			lsLog.add("GetQueryParameter:"+getQueryString);
			lsLog.add("GetRemoteHost:"+getRemoteHost);
			lsLog.add("GetCookieUserName:"+getCookieUserName);
			lsLog.add("GetSessionUserName:"
					+(request.getSession().getAttribute("loginPerson")==null ? "null" 
							: request.getSession().getAttribute("loginPerson")));
			lsLog.add("****************************************");
			String key = getCookieUserName+"|BusinessDesc";
			getLogUtils().info(key,lsLog);
			return true;
		}else{
			return true;
		}
	}
	
	/* (non-Javadoc)  
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)  
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/* (non-Javadoc)  
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)  
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	public LogUtils getLogUtils() {
		return logUtils;
	}

}
