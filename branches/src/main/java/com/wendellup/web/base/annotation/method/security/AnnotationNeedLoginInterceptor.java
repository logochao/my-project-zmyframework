/**  
 * Description: <类功能描述-必填> 
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup 
 * @version:    1.0  
 * Create at:   2013-1-21 23:00:24
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-21   wendellup      1.0       如果修改了;必填  
 */ 
package com.wendellup.web.base.annotation.method.security;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wendellup.web.base.annotation.bind.NeedLogin;

@SuppressWarnings("all")
public class AnnotationNeedLoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		HandlerMethod hd = (HandlerMethod)handler;
		Class controller = hd.getMethod().getDeclaringClass();
		Method[] method = controller.getMethods();
		boolean flag = controller.isAnnotationPresent(NeedLogin.class);
		if(flag){
			//如果未登陆,需要登陆,redirect到登陆界面
			if(request.getSession().getAttribute("sessionAccount")==null){
				NeedLogin needLogin = (NeedLogin) controller.getAnnotation(NeedLogin.class);
				String refr = request.getRequestURI();
				response.sendRedirect(request.getContextPath()+"/login?redirectURL="+refr);
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
}
