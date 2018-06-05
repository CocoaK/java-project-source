package com.quhwa.scalehouse.service.scale.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.service.IEmailClientService;

@Service
public class EmailClientService implements IEmailClientService{

	@Override
	public ResultEntity<String> sendEmail(final String to,final String content, final String acct, final String password) {
		//System.out.println("sendEmail to " + to + "...");
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.SUCCESS,"","");
		Thread t = new Thread(  
                new Thread(){  
                    @Override  
                    public void run() {  
                		try {
                			HtmlEmail email = new HtmlEmail();
                			email.setHostName("smtp.263.net");
                			email.setAuthenticator(new DefaultAuthenticator(acct, password));
                			//email.setAuthenticator(new DefaultAuthenticator("software.rnd@quhwa.com", "11qq22ww"));
                			email.addTo(to);
                			email.setFrom(acct);
                			//主题
                			email.setSubject("Retrieve your password");
                			email.setCharset("utf-8");
                			//email.setSSL(true);
                			//email.setTLS(true);
                			email.setSmtpPort(25);
                			//email.setSmtpPort(465);
                			email.setSocketConnectionTimeout(20*1000);
                			//ssl的smtp端口
                			//email.setSslSmtpPort("587");
                			//html内容
                			email.setHtmlMsg(content);
                			// set the alternative message
                			email.setTextMsg("Your email client does not support HTML messages");
                			// send the email
                			//System.out.println("sending...");
                			email.send();
                			//System.out.println("sent");
                		} catch (Exception e) {
                			e.printStackTrace();
                		}
                    }  
                }  
        );
		t.start();
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage("");
		return re;
	}

}
