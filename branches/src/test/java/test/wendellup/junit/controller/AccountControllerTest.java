/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-28 下午2:47:09
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-28   wendellup     1.0       如果修改了;必填  
 */
package test.wendellup.junit.controller;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wendellup.app.valueobject.entity.Account;

public class AccountControllerTest extends JUnitControllerBase {
    /**
     * 说明： 测试AccountControllerTest的showAccounts方法
     */
    //@Test
    public void showAccounts() throws Exception {
        // 设置模拟的参数
        request.setRequestURI("/account/showAccounts");
        request.setMethod("GET");
        final ModelAndView mav = this.excuteController(request, response);
        // 获取Controller的session的值
        Assert.assertEquals("account/pages/showAccounts", mav.getViewName());
    }
    
    //@Test
    public void regist() throws Exception {
        // 设置模拟的参数
        request.setRequestURI("/account/regist");
        request.setMethod("POST");
        Account account = new Account();
        account.setAccountName("xx");
        account.setAccountPassword("xx");
        request.setParameter("accountName", "xx");
        request.setParameter("accountPassword", "xx");
        final ModelAndView mav = this.excuteController(request, response);
        // 获取Controller的session的值
        Assert.assertEquals(new ModelAndView(
        		new RedirectView("/myframework/account/showAccounts")).getViewName()
        		, mav.getViewName());
    }
    
    @Test
    public void update() throws Exception{
    	request.setRequestURI("/account/update");
        request.setMethod("GET");
        request.setParameter("id", "1");
        final ModelAndView mav = this.excuteController(request, response);
        // 获取Controller的session的值
        Assert.assertEquals("account/pages/update"
        		, mav.getViewName());
    }
}
