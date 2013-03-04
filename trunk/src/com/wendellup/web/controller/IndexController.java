package com.wendellup.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.service.BaseController;

@Controller
@RequestMapping("/index")
//@NeedLogin
public class IndexController extends BaseController{
	private static final String MODULEDESC = "主页模块";

	private final static String INDEX = "index";
	
	@BusinessDesc(MethodDesc="主页面",ModuleDesc=MODULEDESC)
	@RequestMapping(value = INDEX)
	public String index() throws Exception {
	    return this.AutoGetURL();
	}
}
