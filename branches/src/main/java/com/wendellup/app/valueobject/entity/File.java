/*
 * FileName: File.java
 * Author:   lukejia
 * Date:     2013-3-5 下午4:07:32
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.wendellup.app.valueobject.entity;

import org.wendellup.core.dao.DomainObject;

/**
 * @author lukejia
 */
public class File extends DomainObject{
    private Integer id;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件大小
     */
    private String length;
    /**
     * 文件路径
     */
    private String uri;
    
    public File(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
