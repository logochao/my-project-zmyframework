package test.wendellup.junit;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.wendellup.app.dao.contract.visit.IVisitDao;
import com.wendellup.app.dao.impl.visit.VisitDaoImpl;

public class TestVisitDao {
	private IVisitDao iVisitDao = null;
	
	@Before
	public void setUp(){
		iVisitDao = new VisitDaoImpl();
	}
	
	//@Test
//	public void testFindVisitTimesByTime() throws Exception{
//		Assert.assertEquals(1, iVisitDao.findVisitTimesByTime("2012-1-13"));
//	}
	
}
