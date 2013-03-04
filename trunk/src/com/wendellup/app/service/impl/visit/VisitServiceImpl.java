package com.wendellup.app.service.impl.visit;

import javax.annotation.Resource;

import com.wendellup.app.dao.contract.visit.IVisitDao;
import com.wendellup.app.service.contract.visit.IVisitService;
import com.wendellup.app.valueobject.entity.Visit;

//@Service
public class VisitServiceImpl implements IVisitService{
	@Resource//(name="visitDaoImpl")
	private IVisitDao iVisitDao;
	
	@Override
	public int findVisitTimes(Visit visit) throws Exception {
		return iVisitDao.findVisitTimes(visit);
	}

	@Override
	public void saveVisitRecord(Visit visit) throws Exception {
		iVisitDao.saveVisitRecord(visit);
	}
}
