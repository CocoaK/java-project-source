package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.appdata.vo.AddressBookVO;

/**
 * 
 * 类名称：AddressBookJson 类描述： 地址薄Json类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-30 下午6:41:32
 */
public class AddressBookJson extends Json {
	
	private List<AddressBookVO> addressBookList;

	public List<AddressBookVO> getAddressBookList() {
		return addressBookList;
	}

	public void setAddressBookList(List<AddressBookVO> addressBookList) {
		this.addressBookList = addressBookList;
	}

}
