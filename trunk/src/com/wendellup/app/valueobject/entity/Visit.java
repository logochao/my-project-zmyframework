package com.wendellup.app.valueobject.entity;

import java.util.Date;

public class Visit {
	private Integer id;
	//访问来源ip地址
	private String ip;
	//访问时间
	private Date time;
	//访问的路径
	private String url;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
