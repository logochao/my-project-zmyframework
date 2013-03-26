/**  
 * Description: <类功能描述-必填> 获得客户执行的SQL语句
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-24 上午10:55:00
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-24   wendellup     1.0       如果修改了;必填  
 */
package org.wendellup.core.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.wendellup.util.LogUtils;

public class SqlExecutorExt extends SqlExecutor{
	@Autowired
	private LogUtils logUtils;
	
	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public void executeQuery(StatementScope request, Connection conn,
			String sql,Object[] parameters, int skipResults, int maxResults, 
			RowHandlerCallback callback)
			throws SQLException {
		String executer = "";
		logUtils.info("|"+executer+"|sendCommand:"+sql
				+"Parameter:"+Arrays.toString(parameters));
		super.executeQuery(request, conn, sql, parameters, skipResults, 
				maxResults, callback);
		
	}
	
	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public int executeUpdateProcedure(StatementScope statementScope,
			Connection conn,String sql, Object[] parameters) throws SQLException {
		String executer ="";// CookieUtils.getCookie(requestInfo, "cookieKey");// 执行人
		logUtils.info("|" + executer + "|sendCommand:" + sql
				+ "Parameter:"+Arrays.toString(parameters));
		return super.executeUpdateProcedure(statementScope, conn, sql,
				parameters);
	}
	
	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public int executeUpdate(StatementScope statementScope, Connection conn,
			String sql, Object[] parameters) throws SQLException {
		String executer ="";// CookieUtils.getCookie(requestInfo, "cookieKey");// 执行人
		logUtils.info("|" + executer + "|sendCommand:" + sql
				+ "Parameter:"+Arrays.toString(parameters));
		return super.executeUpdate(statementScope, conn, sql, parameters);
	}
	
}
