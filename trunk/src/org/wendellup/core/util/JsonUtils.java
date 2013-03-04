/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-2-1 上午10:04:55
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-2-1   wendellup     1.0       如果修改了;必填  
 */
package org.wendellup.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtils {
	/**
     * JsonConfig cfg = new JsonConfig();
     * <br/>cfg.setRootClass(rootClass);//设置要转换的类型
     * <br/>cfg.setExcludes(new String[]{...});//...为要过滤的字段，过滤这些字段不被解析。
     * <br/>cfg.setIgnoreDefaultExcludes(true);//默认为false，即过滤默认值的key。
     * <br/>cfg.registerJsonBeanProcessor(Date.class,new JsDateJsonBeanProcessor());// 当输出时间格式时，采用和JS兼容的格式输出。
     */
	
	//说明字段
	public final static boolean HELP_JSONCONFIG = true;
	
	/**
     * 把JSON格式数据转换为Bean领域模型
     * @param <T> 领域模型的类型，返回结果会根据此类型来判断返回类型。
     * @param json JSON格式数据
     *          （如果JSON串里的属性在Bean里没有该属性对象的set方法将会报错，
     *              如果要实现过滤XXX字段转换请使用JsonConfig方法。）
     * @param beanClass 要转换的领域模型类型
     * @return 根据beanClass的类型来返回解析的领域模型对象
	 */
	public static<T> T jsonToBean(String json, Class<T> beanClass){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return beanClass.cast(JSONObject.toBean(jsonObject,beanClass));
	}
	
	/**
     *  把JSON格式数据转换为Bean领域模型
     * @param json JSON格式数据
     * @param jsonConfig JSON转换格式配置文件（设置方法详细见底部说明，或本类的HELP_JSONCONFIG常量DOM注释。）
     * @return 解析的领域模型对象
	 */
	public static Object jsonToBean(String json, JsonConfig jsonConfig){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonConfig.getRootClass().cast(JSONObject.toBean(jsonObject, jsonConfig));
	}
	
	/**
     * 把JSON格式数据转换为Bean领域模型集合
     * @param <T> 领域模型的类型，返回结果会根据此类型来判断返回类型。
     * @param json JSON格式数据
     *          （如果JSON串里的属性在Bean里没有该属性对象的set方法将会报错，
     *              如果要实现过滤XXX字段转换请使用JsonConfig方法。）
     * @param beanClass 要转换的领域模型类型
     * @return 根据beanClass的类型来返回解析的领域模型对象集合的泛型类型
	 */
	@SuppressWarnings("unchecked")
	public static<T> List<T> jsonToBeanList(String json, Class<T> beanClass){
		JSONArray jsonArray = JSONArray.fromObject(json);
		Collection<T> collection = JSONArray.toCollection(jsonArray, beanClass);
		List<T> list = new ArrayList<T>();
		for(T t : collection){
			if(beanClass.equals(t.getClass())){
				list.add(t);
			}
		}
		return list;
	}
	
	/**
     * 把JSON格式数据转换为Bean领域模型集合
     * @param json JSON格式数据
     * @param jsonConfig JSON转换格式配置文件（设置方法详细见底部说明，或本类的HELP_JSONCONFIG常量DOM注释。）
     * @return 解析的领域模型对象集合
	 */
	public static Collection<?> jsonToBeanList(String json, JsonConfig jsonConfig){
		JSONArray jsonArray = JSONArray.fromObject(json);
		Collection<?> collection = JSONArray.toCollection(jsonArray,jsonConfig);
		return collection;
	}
	
	/**
     * 把一个对象转换为JSON格式数据
     * @param bean 领域模型对象
     *              （如果要过滤默认值的字段或某些字段的值请使用JsonConfig方法。）
     * @return JSON格式数据
	 */
	public static String beanToJson(Object bean){
		return JSONObject.fromObject(bean).toString();
	}
	
	/**
     * 把一个对象转换为JSON格式数据
     * @param bean 领域模型对象
     * @param jsonConfig JSON转换格式配置文件（设置方法详细见底部说明，或本类的HELP_JSONCONFIG常量DOM注释。）
     * @return JSON格式数据
	 */
	public static String beanToJson(Object bean,JsonConfig jsonConfig){
		return JSONObject.fromObject(bean,jsonConfig).toString();
	}
	
	/**
     * 获取JSON格式数据某个属性值
     * @param json JSON格式数据
     * @param key 要获取的属性名
     * @return 指定属性的值
	 */
	public static Object getJsonAttribute(String json,String key){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject.get(key);
	}
	
	/**
     * 获取JSON格式数据某个属性值
     * @param <T> 属性值的类型，返回结果会根据此类型来判断返回类型。
     * @param json JSON格式数据
     * @param key 要获取的属性名
     * @param attributeClass 要获取的属性类型
     * @return 根据attributeClass的类型来返回该类型的数据
	 */
	public static <T> T getJsonAttribute(String json,String key,Class<T> attributeClass){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return attributeClass.cast(jsonObject.get(key));
	}
	
	/**
	 * 将数组转化为JSON字符串
	 * 〈功能详细描述〉
	 *
	 * @param array
	 * @return
	 */
	public static String array2Json(Object[] array) {
		return JSONArray.fromObject(array).toString();
	}
	
	/**
	 * 将集合对象转化为JSON字符串
	 * 〈功能详细描述〉
	 *
	 * @param collection
	 * @return
	 */
	public static String collection2Json(Collection collection) {
		return JSONArray.fromObject(collection).toString();
	}
}
