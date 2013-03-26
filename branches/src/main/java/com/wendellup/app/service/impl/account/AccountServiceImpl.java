/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     yuchao  
 * @version:    1.0  
 * Create at:   2013-1-28 下午1:51:46
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-28   yuchao     1.0       如果修改了;必填  
 */
package com.wendellup.app.service.impl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wendellup.core.support.QueryParams;

import com.wendellup.app.dao.contract.account.IAccountDao;
import com.wendellup.app.service.contract.account.IAccountService;
import com.wendellup.app.valueobject.entity.Account;

/**
 * @author yuchao
 *
 */
@Service("iAccountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    @Qualifier(value = "iAccountDao")
    private IAccountDao iAccountDao;

    /**
     * 插入一条账号记录
     * 
     * @param {引入参数名} {引入参数说明}
     * @return {返回参数名} {返回参数说明}
     * @exception {说明在某情况下,将发生什么异常}
     */
    @Override
    public Integer save(Account entity) {
        return iAccountDao.save(entity);
    }

    /**
     * {方法的功能/动作描述}
     * 
     * @param {引入参数名} {引入参数说明}
     * @return {返回参数名} {返回参数说明}
     * @exception {说明在某情况下,将发生什么异常}
     */
    @Override
    public Integer update(Account entity) {
    	return iAccountDao.update(entity);
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
    	return iAccountDao.delete(id);
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
    	return iAccountDao.deleteByIds(ids);
    }

    /**
     * {方法的功能/动作描述}
     * 
     * @param {引入参数名} {引入参数说明}
     * @return {返回参数名} {返回参数说明}
     * @exception {说明在某情况下,将发生什么异常}
     */
    @Override
    public Account getById(Integer id) {
        return iAccountDao.getById(id);
    }

    /**
     * {方法的功能/动作描述}
     * 
     * @param {引入参数名} {引入参数说明}
     * @return {返回参数名} {返回参数说明}
     * @exception {说明在某情况下,将发生什么异常}
     */
    @Override
    public List<Account> queryByPage(QueryParams<?> queryParams) {
        return iAccountDao.queryByPage(queryParams);
    }

    /**
     * 查询所有账户记录
     * 
     * @param {引入参数名} {引入参数说明}
     * @return {返回参数名} {返回参数说明}
     * @exception {说明在某情况下,将发生什么异常}
     */
    @Override
    public List<Account> queryAll() {
        return iAccountDao.queryAll();
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
        // TODO Auto-generated method stub
        return 0;
    }

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public int getNumByAccountName(Account account) {
		return iAccountDao.getNumByAccountName(account);
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Account login(Account account) {
		return iAccountDao.login(account);
	}
}
