package com.biencloud.smarthome.service.addressbook.service;

import java.util.List;

import com.biencloud.smarthome.service.addressbook.model.AddressBook;
import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 
 * 类名称：IAddressBookService 
 * 类描述： 地址薄业务处理接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-30 下午5:24:59
 */
public interface IAddressBookService extends IService<AddressBook, Long>{
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
	public Paging<AddressBook> queryAddressBookForPaging(
			 int pageNum, int pageSize,String condition, String orderString, Object... values);
	/**
	 * 
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:22:28
	 * @param addressBook
	 * @return
	 */
	public boolean saveOrUpdateAdressBook(AddressBook addressBook);
	/**
	 * 
	 * 方法的描述: 根据id删除
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-30 下午5:23:48
	 * @param id
	 * @return
	 */
	public boolean deleteByAppId(String deviceNo,Long id);
	/**
	 * 
	 * 方法的描述: 根据设备编号获得所有通讯录
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-25 下午3:19:22
	 * @param deviceNo
	 * @return
	 */
	public List<AddressBook> listAllByDeviceNo(String deviceNo);
	
	
}
