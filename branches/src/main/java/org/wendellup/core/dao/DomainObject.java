/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup 
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-24 上午10:20:32
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-24   wendellup     1.0       如果修改了;必填  
 */
package org.wendellup.core.dao;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 系统领域对象的基类, 实现了Serializable接口。 DomainObject还覆写了hashCode()方法和equals()方法。这两个方法都将基于代理主键进行操作。
 * 只有代理主键相等，领域对象才相等。如果创建的对象的entityId均未赋值，则直接比较物理地址。
 */
public class DomainObject {
	/**
     * 领域对象的代理主键.
     */
	private Integer id;
	
	/**
     * 默认构造函数。
     * 
     */
	public DomainObject(){
		
	}
	
    /**
     * 通过代理主键构造领域对象。
     * 
     * @param id
     */
    public DomainObject(Integer id) {
        this();
        this.setId(id);
    }
    
    /**
     * 覆写基类的hashCode方法，如果代理主键不为null，返回Id对应的hashCode值，否则返回基类的hashCode值。
     * 
     * @return
     */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).toHashCode();
	}
	
	/**
     * 根据代理主键判断领域对象是否相同。
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainObject other = (DomainObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
