/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-22 下午12:48:24
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-22   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LogUtils {
	/** 分隔符 */
	private final static String STR_SEP = "|";
	/** 日志系统MAP集合 */
	private static Map<String, Logger> loggerMap = new HashMap<String, Logger>();

	private static Boolean isinlogfile = true;// true代表打印信息到控制台,false不答应

	/**
	 * 获取日志系统<br> 
	 * 〈功能详细描述〉
	 *
	 * @param logName 日志系统名
	 * @return 日志系统
	 * @see 
	 * @since [1.0]
	 */
	public Logger getLogger(String logName) {
		if (!loggerMap.containsKey(logName)) {
			loggerMap.put(logName, Logger.getLogger(logName));
		}
		return loggerMap.get(logName);
	}

	/**
	 * 获取日志系统
	 * 
	 * @param logClass
	 *            要记录日志的Class
	 * @return 日志系统
     * @see 
     * @since [1.0]
	 */
	public Logger getLogger(Class<?> logClass) {
		if (!loggerMap.containsKey(logClass.getName())) {
			loggerMap.put(logClass.getName(), Logger.getLogger(logClass));
		}
		return loggerMap.get(logClass.getName());
	}

	/**
	 * 记录日志<br> 
	 * 〈功能详细描述〉
	 *
	 * @param mssage
	 * @see 
	 * @since [1.0]
	 */
	public void warn(String mssage) {
		if (isinlogfile) {
			StackTraceElement[] stacks = new Exception().getStackTrace();
			getLogger(stacks[2].getClassName()).warn(
					formatMsg(stacks[2].getClassName(), stacks[2]
							.getMethodName(), mssage));
		}
	}

	/**
	 * 〈一句话功能简述〉<br> 
	 * 〈功能详细描述〉
	 *
	 * @param key
	 * @param ls
	 * @see 
	 * @since [1.0]
	 */
	public void warn(String key, List<String> ls) {
		if (isinlogfile) {
			for (String str : ls) {
				warn(str);
			}
		}
	}

	/**
	 * 记录日志<br> 
	 * 〈功能详细描述〉
	 *
	 * @param mssage
	 * @see 
	 * @since [1.0]
	 */
	public void fatal(String mssage) {
		if (isinlogfile) {
			StackTraceElement[] stacks = new Exception().getStackTrace();
			getLogger(stacks[2].getClassName()).fatal(
					formatMsg(stacks[2].getClassName(), stacks[2]
							.getMethodName(), mssage));
		}

	}

	/**
	 * 〈一句话功能简述〉<br> 
	 * 〈功能详细描述〉
	 *
	 * @param key
	 * @param ls
	 * @see 
	 * @since [1.0]
	 */
	public void fatal(String key, List<String> ls) {
		if (isinlogfile) {
			for (String str : ls) {
				fatal(str);
			}
		}
	}

	/**
	 * 记录日志<br> 
	 * 〈功能详细描述〉
	 *
	 * @param mssage
	 * @see 
	 * @since [1.0]
	 */
	public void info(String mssage) {
		StackTraceElement[] stacks = new Exception().getStackTrace();
		if (isinlogfile) {
			getLogger(stacks[2].getClassName()).info(
					formatMsg(stacks[2].getClassName(), stacks[2]
							.getMethodName(), mssage));
		}
	}

	/**
	 * 〈一句话功能简述〉<br> 
	 * 〈功能详细描述〉
	 *
	 * @param key
	 * @param ls
	 * @see 
	 * @since [1.0]
	 */
	public void info(String key, List<String> ls) {
		if (isinlogfile) {
			for (String str : ls) {
				info(str);
			}
		}
	}

	/**
	 * 记录日志<br> 
	 * 〈功能详细描述〉
	 *
	 * @param mssage
	 * @see 
	 * @since [1.0]
	 */
	public void error(String mssage) {
		StackTraceElement[] stacks = new Exception().getStackTrace();
		if (isinlogfile) {
			getLogger(stacks[2].getClassName()).error(
					formatMsg(stacks[2].getClassName(), stacks[2]
							.getMethodName(), mssage));
		}
	}

	/**
	 * 〈一句话功能简述〉<br> 
	 * 〈功能详细描述〉
	 *
	 * @param key
	 * @param ls
	 * @see 
	 * @since [1.0]
	 */
	public void error(String key, List<String> ls) {
		if (isinlogfile) {
			for (String str : ls) {
				info(str);
			}
		}
	}

	/**
	 * 记录日志<br> 
	 * 〈功能详细描述〉
	 *
	 * @param mssage
	 * @see 
	 * @since [1.0]
	 */
	public void debug(String mssage) {

		StackTraceElement[] stacks = new Exception().getStackTrace();
		if (isinlogfile) {
			getLogger(stacks[2].getClassName()).debug(
					formatMsg(stacks[2].getClassName(), stacks[2]
							.getMethodName(), mssage));
		}
	}

	/**
	 * 记录日志<br> 
	 * 〈功能详细描述〉
	 *
	 * @param key
	 * @param ls
	 * @see 
	 * @since [1.0]
	 */
	public void debug(String key, List<String> ls) {
		if (isinlogfile) {
			for (String str : ls) {
				debug(str);
			}
		}
	}

	/**
	 * 格式化日志信息
	 * 
	 * @param cls
	 *            报告日志的类
	 * @param methodName
	 *            报告日志的方法
	 * @param mssage
	 *            消息
	 * @return 格式化后的日志信息
     * @see 
     * @since [1.0]
	 */
	public String formatMsg(String clsName, String methodName, String mssage) {
		StringBuilder strBuilder = new StringBuilder();
		SimpleDateFormat dataFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		strBuilder.append(dataFormat.format(new Date()));
		strBuilder.append(STR_SEP);
		strBuilder.append(clsName);
		strBuilder.append(STR_SEP);
		strBuilder.append(methodName);
		strBuilder.append(STR_SEP);
		strBuilder.append(mssage);
		return strBuilder.toString();
	}

	/**
	 * 格式化日志信息
	 * 
	 * @param cls
	 *            报告日志的类
	 * @param methodName
	 *            报告日志的方法
	 * @param mssage
	 *            消息
	 * @return 格式化后的日志信息
     * @see 
     * @since [1.0]
	 */
	public String formatMsg(Class<?> cls, String methodName, String mssage) {
		return formatMsg(cls.getName(), methodName, mssage);
	}

}

