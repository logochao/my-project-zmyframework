/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-24 上午10:12:54
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-24   wendellup     1.0       如果修改了;必填  
 */
package org.wendellup.core.dao;

import java.util.List;

import org.wendellup.core.support.QueryParams;

/**
 * 公共DAO接口
 * 公共接口,包括添加、修改、删除、通过id查询、分页查询、查询总数
 */
public interface Dao<T> {
	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	Integer save(T entity);
	
	/**
	 * 修改
	 * 
	 * @param entity
	 * @return
	 */
	Integer update(T entity);
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	Integer delete(Integer id);
	
	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	Integer deleteByIds(String ids);
	
	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(Integer id);
	
	/**
	 * 分页查询
	 * 
	 * @param queryParams
	 * @return
	 */
	List<T> queryByPage(QueryParams<?> queryParams);
	
	/**
	 * 获取所有数据
	 * 
	 * @return
	 */
	List<T> queryAll();
	
	/**
	 * 获取总数
	 * 
	 * @param params
	 * @return
	 */
	Integer getTotalCount(Object params);
}
