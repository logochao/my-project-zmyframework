/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     yuchao  
 * @version:    1.0  
 * Create at:   2013-2-1 上午10:50:19
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-2-1   yuchao     1.0       如果修改了;必填  
 */
package test.wendellup.junit;

/**
 * @author yuchao
 *
 */
public class Address {
	private String province;
	private String city;
	private String road;
	private String postCode;
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the road
	 */
	public String getRoad() {
		return road;
	}
	/**
	 * @param road the road to set
	 */
	public void setRoad(String road) {
		this.road = road;
	}
	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * @param province
	 * @param city
	 * @param road
	 * @param postCode
	 */
	public Address(String province, String city, String road, String postCode) {
		super();
		this.province = province;
		this.city = city;
		this.road = road;
		this.postCode = postCode;
	}
	
	
}
