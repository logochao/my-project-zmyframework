/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     nanjingIT 
 * @author:     nanjingIT  
 * @version:    1.0  
 * Create at:   2013-1-22 下午5:01:25
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-22   nanjingIT     1.0       如果修改了;必填  
 */
package test.wendellup.junit;

import org.apache.log4j.Logger;

public class A1 {
	private static Logger logger = Logger.getLogger(A1.class);
	/**  
	 * 构造方法的描述:A1.  
	 *    
	 */
	public A1() {
		StackTraceElement[] stacks = new Exception().getStackTrace();
		for (int i = 0; i < stacks.length; i++) {
			logger.info(stacks[i]);
		}
	}
	
}
