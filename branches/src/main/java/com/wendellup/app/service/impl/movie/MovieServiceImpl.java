/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-3-1 下午2:28:24
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-3-1   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.app.service.impl.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wendellup.core.support.QueryParams;

import com.wendellup.app.dao.contract.movie.IMovieDao;
import com.wendellup.app.service.contract.movie.IMovieService;
import com.wendellup.app.valueobject.entity.Movie;

@Service("iMovieService")
public class MovieServiceImpl implements IMovieService{
	@Autowired
	@Qualifier(value="iMovieDao")
	private IMovieDao iMovieDao;

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer save(Movie entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer update(Movie entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Movie getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public List<Movie> queryByPage(QueryParams<?> queryParams) {
		return iMovieDao.queryByPage(queryParams);
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public List<Movie> queryAll() {
		return iMovieDao.queryAll();
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer getTotalCount(Object params) {
		return null;
	}
}
