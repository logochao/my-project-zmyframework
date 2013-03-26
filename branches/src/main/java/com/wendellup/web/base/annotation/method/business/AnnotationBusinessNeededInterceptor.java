/**  
 * Description: <类功能描述-必填>  设置必须在业务方法中添加BusinessDesc注解的拦截器
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-22 下午5:25:27
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-22   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.web.base.annotation.method.business;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wendellup.web.base.annotation.bind.BusinessDesc;

public class AnnotationBusinessNeededInterceptor extends HandlerInterceptorAdapter{
	/**
	 * 执行前每个方法前需要添加BusinessDesc描述
	 *	
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @author wendellup
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		HandlerMethod hd = (HandlerMethod)handler;
		Method method = hd.getMethod();
		if(!method.isAnnotationPresent(BusinessDesc.class)){
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(
					"请在"+method.getDeclaringClass().getName()+"."
					+method.getName()+"前添加业务描述");
			return false;
		}
		return true;
	}
}
