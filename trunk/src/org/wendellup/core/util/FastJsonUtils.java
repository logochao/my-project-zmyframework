/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-2-1 上午11:07:08
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-2-1   wendellup     1.0       如果修改了;必填  
 */
package org.wendellup.core.util;

import java.lang.reflect.Type;
import java.nio.charset.CharsetDecoder;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author chenzhao
 * @version [版本号, 2012-12-17]


 */
public class FastJsonUtils extends JSON {
		/**
		 * 功能描述: <br>
		 * 〈功能详细描述〉
		 *
		 * @param text
		 * @return

		
		 */
		public static  Object Fast_parse(String text) {
			return parse(text);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @param features
	     * @return

	
	     */
	    public static  Object Fast_parse(String text, int features) {
	      return parse(text, features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param input
	     * @param features
	     * @return

	
	     */
	    public static  Object Fast_parse(byte[] input, Feature... features) {
	       return parse(input,features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param input
	     * @param off
	     * @param len
	     * @param charsetDecoder
	     * @param features
	     * @return

	
	     */
	    public static  Object Fast_parse(byte[] input, int off, int len, CharsetDecoder charsetDecoder, Feature... features) {
	       return parse(input,  off,  len,  charsetDecoder, features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param input
	     * @param off
	     * @param len
	     * @param charsetDecoder
	     * @param features
	     * @return

	
	     */
	    public static  Object Fast_parse(byte[] input, int off, int len, CharsetDecoder charsetDecoder, int features) {
	        return parse(input,  off,  len,  charsetDecoder,  features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @param features
	     * @return

	
	     */
	    public static  Object Fast_parse(String text, Feature... features) {
	       return parse(text, features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @param features
	     * @return

	
	     */
	    public static  JSONObject Fast_parseObject(String text, Feature... features) {
	    	return parseObject( text,  features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @return

	
	     */
	    public static  JSONObject Fast_parseObject(String text) {
	      return parseObject(text);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @param type
	     * @param features
	     * @return

	
	     */
	    @SuppressWarnings("unchecked")
	    public static  <T> T Fast_parseObject(String text, TypeReference<T> type, Feature... features) {
	      return parseObject(text, type, features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @param clazz
	     * @param features
	     * @return

	
	     */
	    @SuppressWarnings("unchecked")
	    public static  <T> T Fast_parseObject(String text, Class<T> clazz, Feature... features) {
	       return Fast_parseObject( text, clazz, features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param input
	     * @param clazz
	     * @param features
	     * @return

	
	     */
	    @SuppressWarnings("unchecked")
	    public static  <T> T Fast_parseObject(String input, Type clazz, Feature... features) {
	       return (T)parseObject( input,  clazz, features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param input
	     * @param clazz
	     * @param featureValues
	     * @param features
	     * @return

	
	     */
	    @SuppressWarnings("unchecked")
	    public static  <T> T Fast_parseObject(String input, Type clazz, int featureValues, Feature... features) {
	        return (T)parseObject( input,  clazz,  featureValues, features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param input
	     * @param clazz
	     * @param config
	     * @param featureValues
	     * @param features
	     * @return

	
	     */
	    @SuppressWarnings("unchecked")
	    public static  <T> T Fast_parseObject(String input, Type clazz, ParserConfig config, int featureValues,
	                                          Feature... features) {
	        return (T)parseObject( input,  clazz,  config,  featureValues,
                    features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param parser
	     * @param value
	     * @return

	
	     */
	    public static <T> int Fast_handleResovleTask(DefaultJSONParser parser, T value) {
	        return handleResovleTask( parser,value);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param input
	     * @param clazz
	     * @param features
	     * @return

	
	     */
	    @SuppressWarnings("unchecked")
	    public static  <T> T Fast_parseObject(byte[] input, Type clazz, Feature... features) {
	      return (T)Fast_parseObject(input,  clazz,  features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param input
	     * @param off
	     * @param len
	     * @param charsetDecoder
	     * @param clazz
	     * @param features
	     * @return

	
	     */
	    @SuppressWarnings("unchecked")
	    public static  <T> T Fast_parseObject(byte[] input, int off, int len, CharsetDecoder charsetDecoder, Type clazz,
	                                          Feature... features) {
	       return (T)parseObject(input,  off,  len,  charsetDecoder,  clazz,
                    features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param input
	     * @param length
	     * @param clazz
	     * @param features
	     * @return

	
	     */
	    @SuppressWarnings("unchecked")
	    public static  <T> T Fast_parseObject(char[] input, int length, Type clazz, Feature... features) {
	       return (T)parseObject( input,  length,  clazz, features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @param clazz
	     * @return

	
	     */
	    public static  <T> T Fast_parseObject(String text, Class<T> clazz) {
	      return parseObject(text,clazz);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @return

	
	     */
	    public static  JSONArray Fast_parseArray(String text) {
	    		return parseArray(text);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @param clazz
	     * @return

	
	     */
	    public static  <T> List<T> Fast_parseArray(String text, Class<T> clazz) {
	       return parseArray(text,clazz);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param text
	     * @param types
	     * @return

	
	     */
	    public static  List<Object> Fast_parseArray(String text, Type[] types) {
	      return parseArray(text,types);
	    }

	    // ======================

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param object
	     * @return

	
	     */
	    public static  String Fast_toJSONString(Object object) {
	    	return toJSONString(object);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param object
	     * @param features
	     * @return

	
	     */
	    public static  String Fast_toJSONString(Object object, SerializerFeature... features) {
	      return toJSONString(object,features);
	    }

	    /**
	     * @since 1.1.14
	     */
	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param object
	     * @param dateFormat
	     * @param features
	     * @return

	
	     */
	    public static  String Fast_toJSONStringWithDateFormat(Object object, String dateFormat,
	                                                          SerializerFeature... features) {
	        return toJSONStringWithDateFormat(object,dateFormat,features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param object
	     * @param features
	     * @return

	
	     */
	    public static  byte[] Fast_toJSONBytes(Object object, SerializerFeature... features) {
	  
	    		return toJSONBytes(object,features);
	        
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param object
	     * @param config
	     * @param features
	     * @return

	
	     */
	    public static  String Fast_toJSONString(Object object, SerializeConfig config, SerializerFeature... features) {
	       return toJSONString(object,config,features);
	    }

	    /**
         * 功能描述: <br>
         * 〈功能详细描述〉
         *
         * @param object
         * @param filter
         * @param features
         * @return
        
        
         */
        public static  String Fast_toJSONString(Object object, SerializeFilter filter, SerializerFeature... features) {
        
        	return toJSONString(object,filter,features);
        
        }

        /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param object
	     * @param mapping
	     * @param features
	     * @return

	
	     */
	    public static  String Fast_toJSONStringZ(Object object, SerializeConfig mapping, SerializerFeature... features) {
	       return toJSONStringZ(object,mapping,features);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param object
	     * @param config
	     * @param features
	     * @return

	
	     */
	    public static  byte[] Fast_toJSONBytes(Object object, SerializeConfig config, SerializerFeature... features) {
	       return toJSONBytes(object, config,features);
	    }

	    

	    // ======================================

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @return

	
	     */
	    public String Fast_toString() {
	       return toString();
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @return

	
	     */
	    public String Fast_toJSONString() {
	       return toJSONString();
	    }

	    /**
         * 功能描述: <br>
         * 〈功能详细描述〉
         *
         * @param object
         * @param prettyFormat
         * @return
        
        
         */
        public static  String Fast_toJSONString(Object object, boolean prettyFormat) {
           return toJSONString(object, prettyFormat);
        }

        /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param appendable

	
	     */
	    public void Fast_writeJSONString(Appendable appendable) {
	    	writeJSONString(appendable);
	    }

	    // ///////

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param javaObject
	     * @return

	
	     */
	    public static  Object Fast_toJSON(Object javaObject) {
	       return toJSON(javaObject);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param javaObject
	     * @param mapping
	     * @return

	
	     */
	    @SuppressWarnings("unchecked")
	    public static  Object Fast_toJSON(Object javaObject, ParserConfig mapping) {
	        return toJSON(javaObject, mapping);
	    }

	    /**
	     * 功能描述: <br>
	     * 〈功能详细描述〉
	     *
	     * @param json
	     * @param clazz
	     * @return

	
	     */
	    public static  <T> T Fast_toJavaObject(JSON json, Class<T> clazz) {
	    	return toJavaObject(json, clazz);
	    }
}
