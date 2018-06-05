package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.appdata.vo.PasswordVO;
/**
 * 类名称：PasswordJson 
 * 类描述： 设备开锁密码Json
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午10:26:49
 */
@SuppressWarnings("serial")
public class PasswordJson extends Json{
	private List<PasswordVO> passwordList;

	public List<PasswordVO> getPasswordList() {
		return passwordList;
	}

	public void setPasswordList(List<PasswordVO> passwordList) {
		this.passwordList = passwordList;
	}
	
}
