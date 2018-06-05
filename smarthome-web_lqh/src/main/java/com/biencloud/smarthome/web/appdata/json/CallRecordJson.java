package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.device.vo.CallRecordVO;
/**
 * 
 * 类名称：CallRecordJson 
 * 类描述： 通话记录json
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-31 下午5:42:39
 */
public class CallRecordJson extends Json{
	List<CallRecordVO> callRecordList;

	public List<CallRecordVO> getCallRecordList() {
		return callRecordList;
	}

	public void setCallRecordList(List<CallRecordVO> callRecordList) {
		this.callRecordList = callRecordList;
	}
	
}
