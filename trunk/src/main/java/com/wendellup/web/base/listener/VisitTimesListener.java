/**  
 * Description: 用来记录项目被访问次数的listener(一个ip一天只计算一次),且访问结果保存在数据库中,
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup 
 * @version:    1.0  
 * Create at:   2013-1-22 10:06:13
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-22   wendellup      1.0       如果修改了;必填  
 */
package com.wendellup.web.base.listener;

import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wendellup.app.dao.contract.visit.IVisitDao;
import com.wendellup.app.valueobject.entity.Visit;

public class VisitTimesListener implements ServletRequestListener {
	private Log log = LogFactory.getLog(getClass());
	private ApplicationContext applicationContext = null;

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest) arg0
				.getServletRequest();
		HttpSession session = request.getSession();
		ServletContext servletContext = session.getServletContext();
		Visit visit = null;
		IVisitDao visitDaoImpl = null;
		//servletContext中初始化的applicationContext的名字为"org.springframework.web.context.WebApplicationContext.ROOT"
		//也可以通过WebApplicationContextUtils来获取到
		//applicationContext = (ApplicationContext) servletContext
		//		.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
		try {
			visitDaoImpl = (IVisitDao) applicationContext
					.getBean("visitDaoImpl");
			if (session.isNew()) {
//				log.info("-------applicationContext--------");
//				log.info("begin- " + applicationContext + " -end");
//				log.info("-----begin-----");
//				log.info(applicationContext.getBean("logUtils"));
				// 先判断当前ip当天是否已经访问过,如果没有则保存当前访问记录
				visit = new Visit();
				visit.setIp(request.getRemoteAddr()+new Random().nextInt(20));
				visit.setTime(new Date());
				// visit.setUrl(request.getRequestURL().toString());
				if (visitDaoImpl.findVisitTimes(visit) == 0) {
					visit.setUrl(request.getRequestURL().toString());
					visitDaoImpl.saveVisitRecord(visit);
				}
			}
			// 显示今天的访问次数
			visit = null;
			Integer visitTimes = visitDaoImpl.findVisitTimes(visit);
			session.setAttribute("visitTimes", visitTimes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
