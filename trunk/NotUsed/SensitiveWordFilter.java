package com.wendellup.web.base.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SensitiveWordFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest arg0,
			HttpServletResponse arg1, FilterChain arg2)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String content = request.getParameter("content");
		PrintWriter out = response.getWriter();
		if (content!=null && content.indexOf("dog") >= 0) {
			out.println("你输入的字符不合法");
			out.close();
		} else {
			// 调用后续的过滤器
			arg2.doFilter(arg0, arg1);
		}
	}

}
