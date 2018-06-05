package com.biencloud.smarthome.service.rest.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.service.IEmailClientService;

@Service
public class EmailClientService implements IEmailClientService{

	@Override
	public ResultEntity<String> sendEmail(String to,
			String content, String acct, String password) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.SUCCESS,"","");
		HtmlEmail email = new HtmlEmail();
		  email.setHostName("smtp.263.net");
		  try {
			  email.setAuthenticator(new DefaultAuthenticator(acct, password));
			  //email.setAuthenticator(new DefaultAuthenticator("software.rnd@quhwa.com", "11qq22ww"));
			  
			  email.addTo(to);
			  email.setFrom(acct);
			  //主题
			  email.setSubject("Reset your password");
			  email.setCharset("utf-8");
			  //email.setSSL(true);
			  //email.setTLS(true);
			  //email.setSmtpPort(25);
			  email.setSmtpPort(465);
			  //email.setSocketConnectionTimeout(20*1000);
			  //ssl的smtp端口
			  //email.setSslSmtpPort("587");
			  //html内容
			  email.setHtmlMsg(content);
			  // set the alternative message
			  email.setTextMsg("Your email client does not support HTML messages");
			  // send the email
			  email.send();
		} catch (Exception e) {
			e.printStackTrace();
			re.setCode(ResultEntity.FAILD);
			re.setData(e.getMessage());
			return re;
		}
		return re;
	}

}
