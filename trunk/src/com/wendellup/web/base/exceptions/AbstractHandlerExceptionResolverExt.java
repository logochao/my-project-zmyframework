/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     nanjingIT 
 * @author:     nanjingIT  
 * @version:    1.0  
 * Create at:   2013-1-23 下午7:15:21
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-23   nanjingIT     1.0       如果修改了;必填  
 */
package com.wendellup.web.base.exceptions;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.util.WebUtils;

import com.wendellup.util.LogUtils;

public class AbstractHandlerExceptionResolverExt extends AbstractHandlerExceptionResolver{
	private Log logger = LogFactory.getLog(AbstractHandlerExceptionResolverExt.class);
	public static final String DEFAULT_EXCEPTION_ATTRIBUTE = "exception";
	
	@Autowired
	private LogUtils logUtils;
	
	private Properties exceptionMappings;
	
	private Map<String,Integer> statusCodes = new HashMap<String, Integer>();

	private String defaultErrorView;
	
	private Integer defaultStatusCode;
	
	private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;

	/**
	 * 执行前每个方法前需要添加BusinessDesc描述
	 *	
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @author nanjingIT
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		List<String> lsLog = new ArrayList<String>();
		StackTraceElement[] traces = new Throwable().getStackTrace();
		for(StackTraceElement element : traces){
			if(element.getClassName().indexOf("com.wendellup")>-1){
				lsLog.add(element.getClassName()+element.getMethodName()
						+"  LineNumber:"+element.getLineNumber());
			}
		}
		String url = request.getRequestURI();
		String param = request.getQueryString();
		request.setAttribute("ex", ex);
		if(param !=null && param.length()>0){
			url += ("?"+param);
		}
		lsLog.add("ExceptionInfo:");
		lsLog.add("ExceptionIP:"+request.getRemoteHost());
		lsLog.add("ExceptionType:"+ex.getClass().getName());
		lsLog.add("Request Url:"+url);
		lsLog.add("ExceptionInfo:"+ex.getMessage());
		String keyName = ex.getClass().getName()+"|"+url;
		logUtils.info(keyName, lsLog);
		String viewName = determineViewName(ex,request);
		if(viewName != null){
			Integer statusCode = determineStatusCode(request,viewName);
			request.setAttribute("ex", ex);
			if(statusCode != null){
				applyStatusCodeIfPossible(request,response,statusCode);
			}
			return getModelAndView(viewName, ex, request);
		}else{
			return null;
		}
	}

	/**
	 * @param viewName
	 * @param ex
	 * @param request
	 * @return
	 */
	private ModelAndView getModelAndView(String viewName, Exception ex,
			HttpServletRequest request) {
		return getModelAndView(viewName, ex);
	}

	/**
	 * @param viewName
	 * @param ex
	 * @return
	 */
	private ModelAndView getModelAndView(String viewName, Exception ex) {
		ModelAndView mv = new ModelAndView(viewName);
		if(this.getExceptionAttribute() != null){
			if(logger.isDebugEnabled()){
				logger.debug("Exposing Exception as model attribute '"
						+ this.getExceptionAttribute() + "'");
			}
			
			mv.addObject(this.exceptionAttribute, ex);
		}
		return mv;
	}

	/**
	 * @param request
	 * @param response
	 * @param statusCode
	 */
	private void applyStatusCodeIfPossible(HttpServletRequest request,
			HttpServletResponse response, Integer statusCode) {
		if(!WebUtils.isIncludeRequest(request)){
			if(logger.isDebugEnabled()){
				logger.debug("Applying HTTP status code " + statusCode);
			}
			response.setStatus(statusCode);
			request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, statusCode);
		}
	}

	/**
	 * @param request
	 * @param viewName
	 * @return
	 */
	private Integer determineStatusCode(HttpServletRequest request,
			String viewName) {
		if(this.getStatusCodes().containsKey(viewName)){
			return this.getStatusCodes().get(viewName);
		}
		return this.defaultStatusCode;
	}

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	private String determineViewName(Exception ex, HttpServletRequest request) {
		String viewName = null;
		if(this.getExceptionMappings() != null){
			viewName = findMatchingViewName(this.getExceptionMappings(),ex);
		}
		
		String url = request.getRequestURI();
		String param = request.getQueryString();
		if(param != null && param.length()>0){
			url +=("?"+param);
		}
		if(viewName == null && this.getDefaultErrorView() != null){
			if(logger.isDebugEnabled()){
				logger.debug("Resolving to default view '"
						+ this.defaultErrorView + "' for exception of type ["
						+ ex.getClass().getName() + "]");
			}
			viewName = this.defaultErrorView;
		}
		return viewName;
	}

	/**
	 * @param exceptionMappings
	 * @param ex
	 * @return
	 */
	private String findMatchingViewName(Properties exceptionMappings,
			Exception ex) {
		String viewName = null;
		String dominantMapping = null;
		int deepest = Integer.MAX_VALUE;
		for(Enumeration<?> names = exceptionMappings.propertyNames();
				names.hasMoreElements();){
			String exceptionMapping = (String) names.nextElement();
			int depth = getDepth(exceptionMapping, ex);
			if(depth>=0 && depth <deepest){
				deepest = depth;
				dominantMapping = exceptionMapping;
				viewName = exceptionMappings.getProperty(exceptionMapping);
			}
		}
		if(viewName != null && logger.isDebugEnabled()){
			logger.debug("Resolving to view '" + viewName
					+"' for exception of type [" + ex.getClass().getName()
					+"],based on exception mapping [" + dominantMapping + "]");
		}
		
		return viewName;
	}

	/**
	 * @param exceptionMapping
	 * @param ex
	 * @return
	 */
	private int getDepth(String exceptionMapping, Exception ex) {
		return getDepth(exceptionMapping, ex.getClass(),0);
	}

	/**
	 * @param exceptionMapping
	 * @param class1
	 * @param i
	 * @return
	 */
	private int getDepth(String exceptionMapping,
			Class<?> exceptionClass, int depth) {
		if(exceptionClass.getName().contains(exceptionMapping)){
			//Found it!
			return depth;
		}
		//If we've gone as far as we can go and haven't found it
		if(exceptionClass.equals(Throwable.class)){
			return -1;
		}
		return getDepth(exceptionMapping, exceptionClass.getSuperclass(),
				depth + 1);
	}

	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	public Integer getDefaultStatusCode() {
		return defaultStatusCode;
	}

	public void setDefaultStatusCode(Integer defaultStatusCode) {
		this.defaultStatusCode = defaultStatusCode;
	}

	public Properties getExceptionMappings() {
		return exceptionMappings;
	}

	public void setExceptionMappings(Properties exceptionMappings) {
		this.exceptionMappings = exceptionMappings;
	}

	public String getExceptionAttribute() {
		return exceptionAttribute;
	}

	public void setExceptionAttribute(String exceptionAttribute) {
		this.exceptionAttribute = exceptionAttribute;
	}

	public Map<String,Integer> getStatusCodes() {
		return statusCodes;
	}

	public void setStatusCodes(Map<String,Integer> statusCodes) {
		this.statusCodes = statusCodes;
	}
	
	
	
}
