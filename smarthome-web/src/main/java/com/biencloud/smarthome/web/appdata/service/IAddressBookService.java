package com.biencloud.smarthome.web.appdata.service;

import javax.jws.WebParam;

import com.biencloud.smarthome.web.appdata.json.AddressBookJson;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.appdata.vo.AddressBookVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 
 * 类名称：IAddressBookService 
 * 类描述： 地址薄业务处理接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-30 下午5:24:59
 */
public interface IAddressBookService {
   /**
    * 
    * 方法的描述:分页处理 
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-30 下午5:21:01
    * @param pageNum
    * @param pageSize
    * @param condition
    * @param orderString
    * @return
    */
	public AddressBookJson queryAddressBookForPaging(
			 int pageNum, int pageSize,AddressBookVO addressBook,String orderString);
	/**
	 * 
	 * 方法的描述: 获得所有的通讯录记录
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-25 下午3:11:58
	 * @param addressBook
	 * @return
	 */
	public AddressBookJson listAll(AddressBookVO addressBook);
	/**
	 * 
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:22:28
	 * @param addressBook
	 * @return
	 */
	public Json saveOrUpdateAdressBook(String jsonString);
	/**
	 * 
	 * 方法的描述: 根据id删除
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:23:48
	 * @param id
	 * @return
	 */
	public Json deleteById(String deviceNo,Long id);
	
	
}
