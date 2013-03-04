/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     yuchao  
 * @version:    1.0  
 * Create at:   2013-2-1 上午10:49:14
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
public class User {
	private String id;
	private String name;
	private Integer age;
	
	private Address address;
	/**
	 * @param id
	 * @param name
	 * @param age
	 * @param address
	 */
	public User(String id, String name, Integer age, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @param id
	 * @param name
	 * @param age
	 */
	public User(String id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	
	
}
