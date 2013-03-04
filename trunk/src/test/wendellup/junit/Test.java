/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     yuchao  
 * @version:    1.0  
 * Create at:   2013-2-1 下午2:44:33
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-2-1   yuchao     1.0       如果修改了;必填  
 */
package test.wendellup.junit;

import java.io.File;

/**
 * @author yuchao
 *
 */
public class Test {
	public static void main(String[] args) {
		File fileto = new File("D:\\tomcat2\\apache-tomcat-6.0.35-windows-x86\\apache-tomcat-6.0.35\\webapps\\myframework\\fileStore\\20131/00/12/30270/2.jpg");
		System.out.println(fileto.getParentFile());
		if(!fileto.getParentFile().exists()){
			fileto.getParentFile().mkdirs();
		}
	}

}
