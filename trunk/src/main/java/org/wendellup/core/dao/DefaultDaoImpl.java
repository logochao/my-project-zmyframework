/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     nanjingIT 
 * @author:     nanjingIT  
 * @version:    1.0  
 * Create at:   2013-1-25 上午12:01:16
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-25   nanjingIT     1.0       如果修改了;必填  
 */
package org.wendellup.core.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.wendellup.core.support.QueryParams;

@Repository("defaultDao")
public class DefaultDaoImpl<T extends DomainObject> extends DaoImpl<T> implements Dao<T> {

	/**
     *  获取带命名空间的函数名 <br>
     *
     * @param class_
     * @return 返回类型为 ClassName.methodName
     * @see 
     */
	public String getStatement(Class<?> class_){
		StackTraceElement[] stacks = new Exception().getStackTrace();
		return getStatement(class_, stacks[1].getMethodName());
	}
	
    /**
     *   获取带命名空间的函数名<br>
     *
     * @param class_
     * @param methodName
     * @return 返回类型为 ClassName.methodName
     * @see 
     */
	public String getStatement(Class<?> class_, String methodName){
		return class_.getName() + "." + methodName;
	}
	   
	/**
     *   根据T类型来算出带命名空间的函数名<br>
     * 
     *
     * @return 返回类型为 ClassName.methodName
     * @see 
     */
	public String getStatement(){
		StackTraceElement[] stacks = new Exception().getStackTrace();
		return getStatement(stacks[1].getMethodName());
	}
	
    /**
     *   根据T类型来算出带命名空间的函数名<br>
     *
     * @param methodName 方法名
     * @return 返回类型为 ClassName.methodName
     * @see 
     */	
	@SuppressWarnings("unchecked")
	public String getStatement(String methodName){
		ParameterizedType type = ((ParameterizedType)getClass().getGenericSuperclass());
		Class<T> entityClass = (Class<T>)(type.getActualTypeArguments()[0]);
		return getStatement(entityClass, methodName);
	}
	
	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer save(T entity) {
		this.save(getStatement(), entity);
        return entity.getId();
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer update(T entity) {
		return this.update(getStatement(), entity);
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
		return delete(getStatement(), id);
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
		return deleteByIds(getStatement(), ids);
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public T getById(Integer id) {
		return this.getEntity(getStatement(), id);
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public List<T> queryByPage(QueryParams<?> queryParams) {
		return this.query(getStatement(), queryParams);
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public List<T> queryAll() {
		return this.query(getStatement());
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
		return uniqueIntResult(getStatement("queryByPage") + COUNT_STATEMENT_SUFFIX, params);
	}

}
