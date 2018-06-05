package com.quhwa.scalehouse.service.scale.service;

import com.quhwa.scalehouse.common.model.ResultEntity;


public interface IEmailClientService{
	
	/**
	 * 发送邮件
	 * @param to 发送地址
	 * @param content 发送内容
	 * @param acct 发送人账户
	 * @param password 发送人密码
	 * @return
	 */
	ResultEntity<String> sendEmail(String to, String content, String acct,String password);
	
}
