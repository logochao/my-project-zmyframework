/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     nanjingIT 
 * @author:     nanjingIT  
 * @version:    1.0  
 * Create at:   2013-1-25 上午10:04:29
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-25   nanjingIT     1.0       如果修改了;必填  
 */
package test.wendellup.junit.dao;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.wendellup.core.support.Page;
import org.wendellup.core.support.QueryParams;

import com.wendellup.app.dao.contract.movie.IMovieDao;
import com.wendellup.app.valueobject.entity.AccountCondition;
import com.wendellup.util.DatetimeUtils;

@SpringApplicationContext({ "applicationContext.xml"})
public class MovieDaoImplTest extends UnitilsJUnit4{
	@SpringBean("iMovieDao")
	private IMovieDao iMovieDao;
	
	private ApplicationContext applicationContext;

	@Before
	public void Init() {
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
				"applicationContext.xml"});
	}
	
	//@Test
	public void queryAll(){
		System.out.println(iMovieDao);
		System.out.println(iMovieDao.queryAll().size());
		//Assert.assertEquals(iAccountDao.queryAll().size(), 3);
	}
	
	
	@Test
    public void queryByPage(){
	    Page page = new Page();
	    page.setCurrentPage(3);
	    QueryParams queryParams = new QueryParams();
	    queryParams.setPaging(page);
	    Assert.assertEquals(5, iMovieDao.queryByPage(queryParams).size());
    }
    
}
