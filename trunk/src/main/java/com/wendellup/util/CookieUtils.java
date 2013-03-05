/**  
 * Description: <类功能描述-必填>  cookie工具类
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-22 下午2:34:28
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-22   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;

	public static String getCookie(HttpServletRequest request, String cookieName) {
		return getCookie(request, null, cookieName);
	}

	/**
	 * 读取COOKIE 〈功能详细描述〉
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName) {
		String returnStr = "";
		Cookie[] cookies = request.getCookies();
		Cookie sCookie = null;
		String cName = null;
		String cValue = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				sCookie = cookies[i];
				cName = sCookie.getName();
				cValue = sCookie.getValue();
				if (cName.equals(cookieName)) {
					returnStr = cValue;
				}
			}
		}
		return returnStr;
	}

	/**
	 * 设置COOKIE 〈功能详细描述〉
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 *            - 名称 String
	 * @param cookieValue
	 *            - 值 String
	 * @param cookieTime
	 *            -int 时间 -1表示关闭浏览器即失效
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName,
			String cookieValue, int cookieTime) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		String host = request.getServerName();
		cookie.setPath("/");
		cookie.setDomain(host);
		cookie.setMaxAge(cookieTime);
		response.addCookie(cookie);
	}

	/**
	 * 清除COOKIE 〈功能详细描述〉
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 *            - int 时间 -1表示关闭浏览器即失效
	 */
	public static void clearCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
