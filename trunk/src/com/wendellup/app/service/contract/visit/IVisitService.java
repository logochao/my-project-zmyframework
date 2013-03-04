package com.wendellup.app.service.contract.visit;

import com.wendellup.app.valueobject.entity.Visit;

public interface IVisitService {
	/** 查询访问次数 */
	int findVisitTimes(Visit visit) throws Exception;
	
	/** 保存一条当前访问记录(一个ip一天只算一次) */
	void saveVisitRecord(Visit visit) throws Exception;
}
