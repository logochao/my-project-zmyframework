/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-3-1 下午2:52:18
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-3-1   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wendellup.core.support.Page;
import org.wendellup.core.support.QueryParams;

import com.wendellup.app.service.contract.movie.IMovieService;
import com.wendellup.app.valueobject.entity.Movie;
import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.exceptions.BusinessException;
import com.wendellup.web.base.service.BaseController;

@Controller
@RequestMapping("/movie")
public class MovieController extends BaseController{
	
	@Autowired
	@Qualifier("iMovieService")
	private IMovieService iMovieService;
	
	private final static String MODULE_DES = "电影信息模块";
	
	// 显示top250信息(不分页)
	private final static String TOP250ALL = "top250all";
	
	// 显示top250信息(分页)
	private final static String TOP250 = "top250";
	
	/**
	 * 显示top250所有信息(不分页)
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "显示top250信息(不分页)")
	@RequestMapping(value = TOP250ALL)
	public String top250all(HttpServletRequest request) throws Exception {
		try {
			List<Movie> movieList = iMovieService.queryAll();
			request.setAttribute("movieList", movieList);
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return this.AutoGetURL();
	}
	
	/**
	 * 显示top250所有信息(分页)
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "显示top250信息(分页)")
	@RequestMapping(value = TOP250)
	public String top250(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		try {
//			QueryParams queryParams = (QueryParams) session
//					.getAttribute("queryParams");
//			if (queryParams == null) {
//				queryParams = new QueryParams();
//			}
			
			QueryParams queryParams = new QueryParams();
			
			List<Movie> movieList = iMovieService.queryByPage(queryParams);
			session.setAttribute("queryParams", queryParams);
			request.setAttribute("movieList", movieList);
			
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return this.AutoGetURL();
	}
	
	/**
	 * 显示top250所有信息(分页)
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "显示top250信息(分页)")
	@RequestMapping(value = "/"+TOP250+"/currentPage={currentPage}/")
	public String top250(HttpServletRequest request,@PathVariable Integer currentPage) throws Exception {
		HttpSession session = request.getSession();
		try {
//			QueryParams queryParams = (QueryParams) session
//					.getAttribute("queryParams");
//			if (queryParams == null) {
//				queryParams = new QueryParams();
//			}
			Page page = new Page();
			page.setCurrentPage(currentPage);
			
			QueryParams queryParams = new QueryParams();
			
			queryParams.setPaging(page);
			List<Movie> movieList = iMovieService.queryByPage(queryParams);
			session.setAttribute("queryParams", queryParams);
			request.setAttribute("movieList", movieList);
			
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return this.AutoGetURL();
	}
}
