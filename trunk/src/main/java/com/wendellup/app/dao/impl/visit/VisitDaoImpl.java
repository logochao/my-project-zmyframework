package com.wendellup.app.dao.impl.visit;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.wendellup.app.dao.contract.visit.IVisitDao;
import com.wendellup.app.valueobject.entity.Visit;

//@Repository
public class VisitDaoImpl extends SqlMapClientDaoSupport implements IVisitDao {
	@Autowired 
	public void setSqlMapClientForAutowire(SqlMapClient sqlMapClient) { 
		 super.setSqlMapClient(sqlMapClient); 
	} 
	
	@Autowired 
	public void setDataSourceForAutowire(DataSource dataSource) { 
		super.setDataSource(dataSource);
	}
	
	@Override
	public void saveVisitRecord(Visit visit) throws Exception {
		this.getSqlMapClient().insert("insert", visit);
	}

	@Override
	public int findVisitTimes(Visit visit) throws Exception {
		return (Integer) this.getSqlMapClient().queryForObject("findVisitTimes", visit);
	}

}
