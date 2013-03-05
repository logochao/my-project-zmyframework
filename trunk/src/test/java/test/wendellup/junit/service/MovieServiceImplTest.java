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
package test.wendellup.junit.service;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.wendellup.core.support.Page;
import org.wendellup.core.support.QueryParams;

import com.wendellup.app.service.contract.account.IAccountService;
import com.wendellup.app.service.contract.movie.IMovieService;
import com.wendellup.app.valueobject.entity.Account;
import com.wendellup.app.valueobject.entity.AccountCondition;
import com.wendellup.util.DatetimeUtils;

@SpringApplicationContext( { "applicationContext.xml" })
public class MovieServiceImplTest extends UnitilsJUnit4 {
	@SpringBean("iMovieService")
	private IMovieService iMovieService;

	//@Test
	public void queryAll() {
		// System.out.println(iMessageDao.queryAll().size());
		Assert.assertEquals(iMovieService.queryAll().size(), 25);
	}

	 @Test
	public void queryByPage() {
		Page page = new Page();
		page.setCurrentPage(3);
		QueryParams queryParams = new QueryParams();
		queryParams.setPaging(page);
		Assert.assertEquals(5, iMovieService.queryByPage(queryParams).size());
	}
}
