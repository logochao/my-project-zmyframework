/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     nanjingIT 
 * @author:     nanjingIT  
 * @version:    1.0  
 * Create at:   2013-1-25 下午3:13:24
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-25   nanjingIT     1.0       如果修改了;必填  
 */
package com.wendellup.app.service.impl.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wendellup.core.support.QueryParams;

import com.wendellup.app.dao.contract.account.IAccountDao;
import com.wendellup.app.dao.contract.message.IMessageDao;
import com.wendellup.app.service.contract.message.IMessageService;
import com.wendellup.app.valueobject.entity.Message;
import com.wendellup.app.valueobject.vo.MessageVo;

@Service("iMessageService")
public class MessageServiceImpl implements IMessageService {

	@Autowired
	@Qualifier(value="iMessageDao")
	private IMessageDao iMessageDao;
	
	@Autowired
	@Qualifier(value="iAccountDao")
	private IAccountDao iAccountDao;
	
	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer save(Message entity) {
		return iMessageDao.save(entity);
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer update(Message entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer deleteByIds(String ids) {
		return 0;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Message getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public List<Message> queryByPage(QueryParams<?> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public List<Message> queryAll() {
		return iMessageDao.queryAll();
	}

	/**
	 * {方法的功能/动作描述}
	 * 
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Override
	public Integer getTotalCount(Object params) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 查询所有聊天记录(包括用户名)
	 */
	@Override
	public List<MessageVo> queryAllWithAccountName() {
		List<MessageVo> messageVoList = new ArrayList<MessageVo>();
		List<Message> messageList = iMessageDao.queryAll();
		for(Message message : messageList){
			MessageVo messageVo = new MessageVo();
			
			messageVo.setAccountId(message.getAccountId());
			messageVo.setId(message.getId());
			messageVo.setMessageContent(message.getMessageContent());
			messageVo.setMessageTime(message.getMessageTime());
			String accountName = iAccountDao.getById(message.getAccountId()).getAccountName();
			messageVo.setAccountName(accountName);
			messageVoList.add(messageVo);
		}
		
		return messageVoList;
	}

}
