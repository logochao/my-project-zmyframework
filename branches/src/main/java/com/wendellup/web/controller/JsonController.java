package com.wendellup.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.service.BaseController;

@Controller
@RequestMapping("/json")
public class JsonController extends BaseController{
	private final static String MODULE_DES = "JSON模块";
	private static final String JSON_VALID = "valid";
	private static final String JSON_JSON = "json";
	private static final String JSON_ADD = "add";
	private static final String JSON_MODEL1 = "model1";
	private static final String JSON_MAP = "map";
	private static final String JSON_LIST = "list";
	private static final String JSON_GETLIST = "getlist";
	private static final String JSON_ORDERVOMAP = "ordervomap";
	private static final String JSON_LIST2 = "list2";
	private static final String JSON_GETMAP = "getmap";
	private static final String JSON_SUCCESS = "success";
	
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "json测试首页面")
	@RequestMapping(value = JSON_JSON, method = { RequestMethod.GET })
	public String index() {
		return this.AutoGetURL();
	}
}
