package com.biencloud.smarthome.web.sip.VO;

import org.apache.commons.lang3.StringUtils;

import com.biencloud.smarthome.web.common.util.CryptoUtils;

public class SipRegister{
	
	    private Integer id;

	    private String username;
	    
	    private String domain;

	    private String password;

	    private String emailAddress;
	    
	    private Integer type;

	    private String ha1;

	    private String ha1b;

	    private String rpid;
	    
	    public SipRegister(){
	    }

	    public SipRegister(String username, String domain, String password,
				String emailAddress) {
			super();
			this.username = username;
			this.domain = (StringUtils.isEmpty(domain)) ? username + "@sip.ipstar.org" : domain;;
			this.password = password;
			this.emailAddress = (StringUtils.isEmpty(emailAddress)) ? username + "@ipstar.org" : emailAddress;
			this.ha1 = CryptoUtils.encodeByMD5(username + domain + password);
			this.ha1b = CryptoUtils.encodeByMD5(username + "@" + domain + password + emailAddress);
		}

		public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username == null ? null : username.trim();
	    }
	    
	   	public String getDomain() {
	        return domain;
	    }

	    public void setDomain(String domain) {
	        this.domain = domain == null ? null : domain.trim();
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password == null ? null : password.trim();
	    }

	    public String getEmailAddress() {
	        return emailAddress;
	    }

	    public void setEmailAddress(String emailAddress) {
	        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
	    }

	    public String getHa1() {
	        return ha1;
	    }

	    public void setHa1(String ha1) {
	        this.ha1 = ha1 == null ? null : ha1.trim();
	    }

	    public String getHa1b() {
	        return ha1b;
	    }

	    public void setHa1b(String ha1b) {
	        this.ha1b = ha1b == null ? null : ha1b.trim();
	    }

	    public String getRpid() {
	        return rpid;
	    }

	    public void setRpid(String rpid) {
	        this.rpid = rpid == null ? null : rpid.trim();
	    }

		public void setEncryptPassword(String pwd){
	    	this.password = pwd;
	    	this.ha1 = CryptoUtils.encodeByMD5(username + domain + password);
			this.ha1b = CryptoUtils.encodeByMD5(username + "@" + domain + password + emailAddress);
	    }

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

}
