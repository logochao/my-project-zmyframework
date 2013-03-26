/**  
 * Description: <类功能描述-必填>  DAO基类针对数据库的读、写操作分别采用不同的Template,可以在一定程度上防止大量并发造成的死锁。 
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-24 上午11:07:54
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-24   wendellup     1.0       如果修改了;必填  
 */
package org.wendellup.core.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wendellup.core.support.QueryParams;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.wendellup.util.BeanUtils;
import com.wendellup.util.LogUtils;

public class DaoImpl<T extends DomainObject> {
	@Resource
	private LogUtils logUtils;
	
	private SqlExecutor sqlExecutor;
	@Autowired
	@Qualifier("SqlExecutorExt")
	public void setSqlExecutor(SqlExecutor sqlExecutor){
		this.sqlExecutor = sqlExecutor;
	}
	
	@SuppressWarnings("deprecation")
	@PostConstruct
	public void initialize() throws Exception{
		if(sqlExecutor != null){
			SqlMapClient sqlMapClient = getWriteTemplate().getSqlMapClient();//只监控插入、更新、删除的SQL
			if(sqlMapClient instanceof ExtendedSqlMapClient){
				BeanUtils.setFieldValue(((ExtendedSqlMapClient)sqlMapClient)
						.getDelegate(), "sqlExecutor", SqlExecutor.class,sqlExecutor
					);
			}
		}
	}
	
	/**
	 * 分页statement后缀:_count
	 */
	protected static final String COUNT_STATEMENT_SUFFIX = "_count";
	
	private String entityClass;
	
	/**
	 * 用于写操作的Template
	 */
	private SqlMapClientTemplate writeTemplate;

	/**
	 * 用于读操作的Template
	 */
	private SqlMapClientTemplate readTemplate;

	public SqlMapClientTemplate getWriteTemplate() {
		return writeTemplate;
	}
	
	/**
	 * 需要在配置文件中配置id为writeSqlMapClient的bean
	 * 
	 * @param sqlMapClient
	 *            the SqlMapClient object to set
	 */
	@Autowired
	@Qualifier("writeSqlMapClient")
	public void setWriteSqlMapClient(SqlMapClient sqlMapClient){
		writeTemplate = new SqlMapClientTemplate(sqlMapClient);
	}
	
	public SqlMapClientTemplate getReadTemplate(){
		return readTemplate;
	}
	
	/**
	 * 需要在配置文件中配置id为writeSqlMapClient的bean
	 * 
	 * @param sqlMapClient
	 *            the SqlMapClient object to set
	 */
	@Autowired
	@Qualifier("readSqlMapClient")
	public void setReadSqlMapClient(SqlMapClient sqlMapClient){
		readTemplate = new SqlMapClientTemplate(sqlMapClient);
	}
	
	/**
	 * 新增实体类根据泛型新增
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	protected DomainObject save(String statement, DomainObject entity){
		Integer entityId = (Integer) writeTemplate.insert(statement, entity);
		if(entityId != null && entityId>0){
			entity.setId(entityId);
		}
		return entity;
	}
	
	/**
	 * 跟据对象新增 <br>
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	protected Integer saveByObj(String statement, Object entity){
		Integer entityId = (Integer) writeTemplate.insert(statement, entity);
		return entityId;
	}
	
	/**
	 * 新增返回本次会话自增Id。 <br>
	 * 
	 * @param statement
	 * @param entity
	 * @return
	 * @see
	 */
	protected int savegetId(String statement, Object entity) {
		return (Integer) (writeTemplate.insert(statement, entity));
	}
	
	/**
	 * 新增返回本次会话自增id<br>
	 * 
	 * @param statement
	 * @param entity
	 * @see
	 */
	protected void saveid(String statement, Object entity) {
		writeTemplate.insert(statement, entity);
	}
	
	/**
	 * 修改<br>
	 * @param statement
	 * @param params
	 * @see
	 */
	protected Integer update(String statement, Object params) {
		return writeTemplate.update(statement, params);
	}
	
	/**
	 * 删除 <br>
	 * @param statement
	 * @param params
	 * @see
	 */
	protected Integer delete(String statement, Object params) {
		return writeTemplate.delete(statement, params);
	}
	
	/**
	 * 批量删除 <br>
	 * @param statement
	 * @param params
	 * @see
	 */
	protected Integer deleteByIds(String statement, Object params) {
		return writeTemplate.delete(statement, params);
	}
	
	/**
	 * 根据参数取得<code>T</code>类型实体<br>
	 * @param statement
	 * @param params
	 * @return
	 * @see
	 */
	@SuppressWarnings("unchecked")
	protected T getEntity(String statement, Object params) {
		return (T) readTemplate.queryForObject(statement, params);
	}
	
	/**
	 * 根据参数取得任意类型实体 <br>
	 * @param statement
	 * @param param
	 * @return
	 * @see
	 */
	protected Object getObject(String statement, Object param) {
		return readTemplate.queryForObject(statement, param);
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> query(String statement, QueryParams<?> params){
		if(params!=null && params.getPaging()!=null){
			int records = queryCount(statement, params);
			//如果查询出符合条件的记录数位0, 那么就直接返回一个空的List,因为后面的已经没有执行的必要
			if(records == 0){
				return new ArrayList<T>(0);
			}
			params.getPaging().setRecords(records);
		}
		return (List<T>)readTemplate.queryForList(statement,params);
	}
	
	/**
	 * 根据参数查询列表，可分页<br>
	 * 
	 * @param statement
	 * @param params
	 * @return
	 * @see
	
	 */
	@SuppressWarnings("unchecked")
	protected List<T> queryEntities(String statement, QueryParams<?> params) {
		if (params != null && params.getPaging() != null) {
			int records = queryCount(statement, params);

			// 如果查询出符合条件的记录数为0，那么就直接返回一个空的List，因为后面的已经没有执行的必要
			if (records == 0) {
				return new ArrayList<T>(0);
			}

			params.getPaging().setRecords(records);
		}
		return readTemplate.queryForList(statement, params);
	}
	
	/**
	 * 查询列表，不提供分页功能 <br>
	 * 
	 * @param statement
	 * @param params
	 * @return
	 * @see
	
	 */
	@SuppressWarnings("unchecked")
	protected List<T> query(String statement, Object params) {
		return (List<T>) readTemplate.queryForList(statement, params);
	}

	/**
	 * 无参数查询列表，不提供分页功能 <br>
	 * 
	 * @param statement
	 * @return
	 * @see
	
	 */
	@SuppressWarnings("unchecked")
	protected List<T> query(String statement) {
		logUtils.info(statement);
		return (List<T>) readTemplate.queryForList(statement);
	}
	
	/**
	 * 查询任意类型的对象列表。不局限于T类型的<br>
	 * 
	 * @param statement
	 * @param params
	 * @return
	 * @see
	
	 */
	@SuppressWarnings("unchecked")
	protected List<T> queryEntities(String statement, Object params) {
		return readTemplate.queryForList(statement, params);
	}
	
	/**
	 * 根据参数判断该记录是否已存在（逻辑上存在）<br>
	 * 
	 * @param statement
	 * @param params
	 * @return
	 * @see
	 */
	protected boolean isExistEntity(String statement, Object params) {
		return (Integer) readTemplate.queryForObject(statement, params) > 0;
	}
	
	/**
	 * 取得指定的statement的完全限定名称。形式为<code>namespace</code> + "." +
	 * <code>statement</code> <br>
	 * 
	 * @param namespace
	 * @param statement
	 * @return
	 * @see
	 */
	protected String getQualifiedName(String namespace, String statement) {
		return new StringBuffer().append(namespace).append(".")
				.append(statement).toString();
	}
	
	/**
	 * 
	 * 取得指定的statement的完全限定名称。该方法以泛型<code>T</code>的实际类型的完全限定名 + "." +
	 * <code>statement</code>。这就要求在 书写iBatis配置文件时，namespace必须写成“泛型<code>T</code>
	 * 的实际类型的完全限定名”。<br>
	 * 例如，如果有DAO实现类TravelGuide&lt;TravelGuide&gt; 那么调用该方法将返回 <br>
	 * 
	 * @param statement
	 * @return
	 * @see
	
	 */
	@SuppressWarnings("unchecked")
	protected String getQualifiedName(String statement) {
		/**
		 * 避免每次都去获取
		 */
		if (StringUtils.isBlank(entityClass)) {
			entityClass = DomainObject.class.getName();
			Type superClass = getClass().getGenericSuperclass();
			if (superClass instanceof ParameterizedType) {
				ParameterizedType genericType = (ParameterizedType) superClass;
				Type[] typeArgs = genericType.getActualTypeArguments();
				if (typeArgs.length > 0) {
					entityClass = ((Class<T>) typeArgs[0]).getName();
				}
			}
		}

		return new StringBuffer().append(entityClass).append(".")
				.append(statement).toString();
	}
	
	/**
	 * 根据条件查询整数结果。<br>
	 * 
	 * @param statement
	 * @param params
	 * @return
	 * @see
	
	 */
	protected int uniqueIntResult(String statement, Object params) {
		if (params == null) {
			return (Integer) readTemplate.queryForObject(statement);
		}
		return (Integer) readTemplate.queryForObject(statement, params);
	}
	
	/**
	 * 查询符合条件的记录数，仅供分页查询调用。 <br>
	 * 增加params非空判断，如果为空表示查询所有的数量
	 * 
	 * @param statement
	 * @param params
	 * @return
	 * @see
	
	 */
	protected int queryCount(String statement, QueryParams<?> params) {
		if (params == null) {
			return (Integer) readTemplate.queryForObject(statement
					+ COUNT_STATEMENT_SUFFIX);
		}
//		int num;
//		num = (Integer) readTemplate.queryForObject(statement
//		        + COUNT_STATEMENT_SUFFIX, params);
//		
//		System.out.println(num);
//		return num;
		return (Integer) readTemplate.queryForObject(statement
				+ COUNT_STATEMENT_SUFFIX, params);

	}

}
