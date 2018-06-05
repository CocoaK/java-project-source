package com.biencloud.smarthome.web.appdata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.json.AddressBookJson;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.appdata.service.IAddressBookService;
import com.biencloud.smarthome.web.appdata.vo.AddressBookVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.common.util.MapUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.wsclient.stub.AddressBook;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 
 * 类名称：AddressBookServiceImpl 类描述： 地址薄业务处理接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-30 下午5:24:35
 */

public class AddressBookServiceImpl extends BaseService<AddressBookVO> implements IAddressBookService {

	@Override
	public AddressBookJson queryAddressBookForPaging(int pageNum, int pageSize, AddressBookVO addressBook, String orderString) {
		AddressBookJson abJson = new AddressBookJson();
		if (addressBook != null && addressBook.getDeviceNo() != null) {
			MapUtil.clearMap();
			MapUtil.addKeyValueToMap("deviceNo", addressBook.getDeviceNo());
			String hql = MapUtil.covertMapKeyToCondition();
			List<Object> list = MapUtil.covertMapValueToObjectList();
			Paging paging = getSmartHomeService().queryAddressBookForPaging(pageNum, pageSize, hql, null, list);
			PagingVO<AddressBookVO> pv = super.convertToPagingVO(paging, "addTime");
			abJson.setAddressBookList(pv.getResults());
			abJson.setCode(AppDataConstant.SUCCESS);
		} else {
			abJson.setCode(AppDataConstant.FAILTRUE);
		}
		return abJson;
	}
	@Override
	public AddressBookJson listAll(AddressBookVO addressBook) {
		AddressBookJson abJson = new AddressBookJson();
		if(addressBook!=null&&addressBook.getDeviceNo()!=null)
		{			
			List<AddressBook> addressBookList=getSmartHomeService().listAllAddressBookByDeviceNo(addressBook.getDeviceNo());
			List<AddressBookVO> list=new ArrayList<AddressBookVO>();
			listCopyToList(addressBookList,list);
			abJson.setAddressBookList(list);
			abJson.setCode(AppDataConstant.SUCCESS);
		}else {
			abJson.setCode(AppDataConstant.FAILTRUE);
		}
		return abJson;
	}
	/**
	 * 
	 * 方法的描述: list复制
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-25 下午4:20:07
	 * @param sourceList
	 * @param targetList
	 */
	private void listCopyToList(List<AddressBook> sourceList,List<AddressBookVO> targetList)
	{
		if(sourceList!=null&&!sourceList.isEmpty()&&targetList!=null)
		{
			for(AddressBook ab:sourceList)
			{
				if(ab!=null)
				{
					AddressBookVO abv=new AddressBookVO(ab.getNickName(), ab.getAreaCode(), ab.getRegionCode(), ab.getStoreyCode(), ab.getUnitCode(), ab.getRoomCode(),
							ab.getIsBlack(), DateTimeUtil.convertXMLGregorianCalendarToDate(ab.getAddTime(), "yyyy-MM-dd HH:mm:ss"), ab.getIp(), ab.getDeviceNo(),ab.getAppId());
					targetList.add(abv);
				}
			}
		}
	}
	@Override
	public Json saveOrUpdateAdressBook(String jsonString) {
		Json js = new Json();		
		if (jsonString != null&&jsonString.startsWith("{")&&jsonString.endsWith("}")) {
			 JSONObject jb=JsonUtil.jsonStringToJsonObject(jsonString);
			AddressBook ab = new AddressBook();
			ab.setAddTime(DateTimeUtil.convertDateToXMLGregorianCalendar(new Date()));
			ab.setAreaCode(JsonUtil.getDataFromJsonObject(jb, "areaCode"));
			ab.setDeviceNo(JsonUtil.getDataFromJsonObject(jb, "deviceNo"));
			//ab.setId(JsonUtil.getDataFromJsonObject(jb, "deviceNo")addressBook.getId());
			ab.setIp(JsonUtil.getDataFromJsonObject(jb, "ip"));
			ab.setIsBlack(JsonUtil.getDataFromJsonObject(jb, "isBlack"));
			ab.setNickName(JsonUtil.getDataFromJsonObject(jb, "nickName"));
			ab.setRegionCode(JsonUtil.getDataFromJsonObject(jb, "regionCode"));
			ab.setRoomCode(JsonUtil.getDataFromJsonObject(jb, "roomCode"));
			ab.setStoreyCode(JsonUtil.getDataFromJsonObject(jb, "storeyCode"));
			ab.setUnitCode(JsonUtil.getDataFromJsonObject(jb, "unitCode"));
			String appId=JsonUtil.getDataFromJsonObject(jb, "id");
			ab.setAppId(appId==null?null:Long.parseLong(appId));
			boolean flag = this.getSmartHomeService().saveOrUpdateAdressBook(ab);
			if (flag) {
				js.setCode(AppDataConstant.SUCCESS);
			} else {
				js.setCode(AppDataConstant.FAILTRUE);
			}
			js.setDeviceNo(JsonUtil.getDataFromJsonObject(jb, "deviceNo"));
		}
		return js;
	}

	@Override
	public Json deleteById(String deviceNo,Long id) {
		Json js = new Json();
		// TODO Auto-generated method stub
		boolean flag = this.getSmartHomeService().deleteAdressBookById(deviceNo,id);
		if (flag) {
			js.setCode(AppDataConstant.SUCCESS);
		} else {
			js.setCode(AppDataConstant.FAILTRUE);
		}
		return js;
	}

	

}
