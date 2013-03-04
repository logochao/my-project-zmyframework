package com.wendellup.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.service.BaseController;

@Controller
@RequestMapping("/common")
//@NeedLogin
public class CommonController extends BaseController{
	private static final String MODULED_DESC = "通用模块";
	
	private static final String ONLINE_PERSON_LIST = "onlinePersonList";
	
    @BusinessDesc(ModuleDesc = MODULED_DESC, MethodDesc = "显示在线人员列表")
    @RequestMapping(value = ONLINE_PERSON_LIST, method = { RequestMethod.GET, RequestMethod.POST })
	public String onlinePersonList(HttpServletRequest request) throws Exception {
		return "conlinePersonList";
	}

}
