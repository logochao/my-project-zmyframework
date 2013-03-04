package com.wendellup.app.dao.contract.visit;

import com.wendellup.app.valueobject.entity.Visit;



public interface IVisitDao {
	/** 查询访问次数 */
	int findVisitTimes(Visit visit) throws Exception;
	
	/** 保存一条当前访问记录 */
	void saveVisitRecord(Visit visit) throws Exception;
}
