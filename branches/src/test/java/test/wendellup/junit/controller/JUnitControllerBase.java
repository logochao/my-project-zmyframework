/**  
 * Description: 说明: JUnit测试controller时使用的基类
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-28 下午2:12:08
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-28   wendellup     1.0       如果修改了;必填  
 */
package test.wendellup.junit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

public class JUnitControllerBase {
    protected static HandlerMapping handlerMapping;
    protected static HandlerAdapter handlerAdapter;
    
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    
    /**
     * 读取spring3 MVC配置文件
     */
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        if(handlerMapping == null){
            //需要加载Junit的配置文件,因为DefaultServlet配置在web.xml
            String[] configs = {"applicationContext.xml","junit/junit-mvc-servlet.xml"};
            XmlWebApplicationContext context = new XmlWebApplicationContext();
            context.setConfigLocations(configs);
            MockServletContext msc = new MockServletContext();
            context.setServletContext(msc);
            context.refresh();
            msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
                    context);
            handlerMapping = (HandlerMapping) context.getBean(DefaultAnnotationHandlerMapping.class);
            handlerAdapter = (HandlerAdapter) context.getBean(context
                    .getBeanNamesForType(AnnotationMethodHandlerAdapter.class)[0]);
            
        }
    }
    
    /**
     * 执行request对象请求的action
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView excuteController(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true); //这里需要声明request的实际类型，否则会报错
        HandlerExecutionChain chain = handlerMapping.getHandler(request);
        final ModelAndView model = handlerAdapter.handle(request, response,chain.getHandler());
        return model;
    }
    
}
