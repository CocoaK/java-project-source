package com.biencloud.smarthome.service.addressbook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.addressbook.model.AddressBook;
import com.biencloud.smarthome.service.addressbook.service.IAddressBookService;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;

/**
 * 
 * 类名称：AddressBookServiceImpl 类描述： 地址薄业务处理接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-30 下午5:24:35
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AddressBookServiceImpl extends BaseService<AddressBook, Long> implements IAddressBookService {

	@Override
	public Paging<AddressBook> queryAddressBookForPaging(int pageNum, int pageSize,String condition, String orderString, Object... values) {
		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, "AddressBook", condition, orderString,values);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveOrUpdateAdressBook(AddressBook addressBook) {
		boolean flag = false;
		if (addressBook != null) {

			AddressBook ab = this.queryAddressBookByAppId(addressBook.getDeviceNo(),addressBook.getAppId());
			if (ab != null) {
				ab.setAddTime(new Date());
				ab.setAreaCode(addressBook.getAreaCode());
				ab.setDeviceNo(addressBook.getDeviceNo());
				ab.setIp(addressBook.getIp());
				ab.setIsBlack(addressBook.getIsBlack());
				ab.setNickName(addressBook.getNickName());
				ab.setRegionCode(addressBook.getRegionCode());
				ab.setRoomCode(addressBook.getRoomCode());
				ab.setStoreyCode(addressBook.getStoreyCode());
				ab.setUnitCode(addressBook.getUnitCode());
			} else {
				ab = super.save_update(addressBook);
				if (ab != null) {
					flag = true;
				}
			}
		}
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteByAppId(String deviceNo,Long id) {
		boolean flag = false;
		AddressBook addressBook = this.queryAddressBookByAppId(deviceNo,id);
		if (addressBook != null) {
			super.remove(addressBook);
			flag = true;
		}
		return flag;
	}

	/**   
	 * 
	 * 方法的描述: 根据appId查询
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-18 下午1:50:35
	 * @param appId
	 * @return
	 */
	private AddressBook queryAddressBookByAppId(String deviceNo,Long appId) {
		AddressBook addressBook = null;
		if (appId != null) {
			List<AddressBook> list = super.find("from AddressBook where appId=? and deviceNo=?", appId,deviceNo);
			if (list != null && !list.isEmpty()) {
				addressBook = list.get(0);

			}
		}
		return addressBook;
	}

	@Override
	public List<AddressBook> listAllByDeviceNo(String deviceNo) {
		List<AddressBook> list=null;
		if(deviceNo!=null)
		{
			list=super.find("from AddressBook where deviceNo=?", deviceNo);
		}
		return list;
	}
}
