package com.biencloud.smarthome.web.device.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.common.util.FileUploadUtil;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.ICallRecordService;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.CallRecordVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;
import com.biencloud.smarthome.web.wsclient.stub.Device;
import com.biencloud.smarthome.web.wsclient.stub.DeviceType;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.CallRecord;

/**
 * 
 * 类名称：CallRecordServiceImpl 
 * 类描述： 
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-16 下午8:36:59
 */
public class CallRecordServiceImpl extends BaseService<CallRecordVO> implements ICallRecordService{

	private static final String CALL_TIME = "callTime";
	private static final String BEGIN_TIME = "beginTime";
	private static final String END_TIME = "endTime";
	private static final String DEVICE = "device";
	private IDeviceService deviceService;
	private ISysParamService sysParamService;
	
	@Override
	public CallRecordVO getCallRecordById(String id) {
		CallRecordVO callRecordVO = new CallRecordVO();
		DeviceTypeVO deviceTypeVO = new DeviceTypeVO();
		CallRecord callRecord = getSmartHomeService().getCallRecordById(id);
		DeviceVO dvo = new DeviceVO();
		//转换Device中的deviceType
		copyProperties(callRecord.getDevice().getDeviceType(),deviceTypeVO);
		//转换CallRecord到CallRecordVO忽略Device属性
		copyProperties(callRecord, callRecordVO,new String[]{DEVICE},true,CALL_TIME,BEGIN_TIME,END_TIME);
		//转换Device到deviceVO
		dvo = deviceService.deviceToVO(callRecord.getDevice());
		//赋值deviceType
		dvo.setDeviceType(deviceTypeVO);
		//赋值Device
		callRecordVO.setDevice(dvo);
		return callRecordVO;
	}

	@Override
	public PagingVO<CallRecordVO> queryCallRecordForPaging(
			CallRecordVO callRecord, int pageNum, int pageSize) {
		CallRecord _callRecord = new CallRecord();
		DeviceType deviceType = new DeviceType();
		Device d = new Device();
		PagingVO<CallRecordVO> pagingVO;
		if(callRecord != null){
			//转换DeviceVO中的deviceTypeVO
			if(callRecord.getDevice()!=null)
				copyProperties(callRecord.getDevice().getDeviceType(),deviceType);
			//转换DeviceVO到device
			d = deviceService.voToDevice(callRecord.getDevice());
			//转换CallRecordVO到CallRecord忽略DeviceVO属性
			copyProperties(callRecord, _callRecord,new String[]{DEVICE},false, CALL_TIME,BEGIN_TIME,END_TIME);
			//赋值deviceType
			d.setDeviceType(deviceType);
			//赋值Device
			_callRecord.setDevice(d);
		}
		Paging paging = getSmartHomeService().queryCallRecordForPaging(_callRecord, pageNum, pageSize);
		pagingVO = convertToPagingVO(paging,new String[]{DEVICE},CALL_TIME,BEGIN_TIME,END_TIME);
		return pagingVO;
	}

	@Override
	public void saveCallRecord(CallRecordVO callRecord) {
		CallRecord _callRecord = new CallRecord();
		if(callRecord != null){
			Device d = new Device();
			DeviceType deviceType = new DeviceType();
			copyProperties(callRecord.getDevice().getDeviceType(),deviceType);
			copyProperties(callRecord, _callRecord,new String[]{DEVICE},false, CALL_TIME,BEGIN_TIME,END_TIME);
			d = deviceService.voToDevice(callRecord.getDevice());
			d.setDeviceType(deviceType);
			_callRecord.setDevice(d);
		}
		getSmartHomeService().saveCallRecord(_callRecord);
	}

	@Override
	public void removeCallRecord(CallRecordVO callRecord) {
		CallRecord _callRecord = new CallRecord();
		if(callRecord != null){
			Device d = new Device();
			DeviceType deviceType = new DeviceType();
			copyProperties(callRecord, _callRecord,new String[]{DEVICE},false, CALL_TIME,BEGIN_TIME,END_TIME);
			d = deviceService.voToDevice(callRecord.getDevice());
			d.setDeviceType(deviceType);
			_callRecord.setDevice(d);
		}
		getSmartHomeService().removeCallRecord(_callRecord);
	}

	@Override
	public List<CallRecordVO> queryCallRecords(CallRecordVO callRecord) {
		String appDownloadUrl = sysParamService.getAppDownloadAbsoluteUrl();
		List<CallRecordVO> list = new ArrayList<CallRecordVO>();
		CallRecord cr = new CallRecord();
		Device d = new Device();
		DeviceType deviceType = new DeviceType();
		if(callRecord==null)
			callRecord = new CallRecordVO();
		//将CallRecordVO转换成//将CallRecord，排除DeivceVO属性
		copyProperties(callRecord, cr,new String[]{DEVICE},false, CALL_TIME,BEGIN_TIME,END_TIME);	
		//将DeviceVO转换成Device
		d = deviceService.voToDevice(callRecord.getDevice());
		//将DeviceTypeVO转换成deviceType
		if(callRecord.getDevice()!=null)
			copyProperties(callRecord.getDevice().getDeviceType(), deviceType);
		//将DeviceType赋值到d
		d.setDeviceType(deviceType);
		//将Device赋给cr
		cr.setDevice(d);
		List<CallRecord> callRecords = getSmartHomeService().queryCallRecords(cr);
		if(callRecords == null || callRecords.size() == 0)
			return list;
		for (CallRecord record : callRecords) {
			CallRecordVO callRecordVO = new CallRecordVO();
			DeviceVO dvo = new DeviceVO();
			DeviceTypeVO deviceTypeVO = new DeviceTypeVO();
			//APP取文件传全路径
			if(StringUtils.isNotBlank(record.getAudioLocalPath()))
				record.setAudioLocalPath(record.getAudioLocalPath());
			if(StringUtils.isNotBlank(record.getAudioNetPath()))
				record.setAudioNetPath(appDownloadUrl+record.getAudioNetPath());
			if(StringUtils.isNotBlank(record.getPictureLocalPath()))
				record.setPictureLocalPath(record.getPictureLocalPath());
			if(StringUtils.isNotBlank(record.getPictureNetPath()))
				record.setPictureNetPath(appDownloadUrl+record.getPictureNetPath());
			if(StringUtils.isNotBlank(record.getVideoLocalPath()))
				record.setVideoLocalPath(record.getVideoLocalPath());
			if(StringUtils.isNotBlank(record.getVideoNetPath()))
				record.setVideoNetPath(appDownloadUrl+record.getVideoNetPath());
			//将CallRecord转换成//将CallRecordVO，排除Deivce属性
			copyProperties(record, callRecordVO,new String[]{DEVICE},true, CALL_TIME,BEGIN_TIME,END_TIME);
			//将Device转换成DeviceVO
			dvo = deviceService.deviceToVO(record.getDevice());
			//转换deviceType到deviceTypeVO
			copyProperties(record.getDevice().getDeviceType(),deviceTypeVO);
			//将DeviceTypeVO赋值给dvo
			dvo.setDeviceType(deviceTypeVO);
			//将DeviceVO赋给callRecordVO
			callRecordVO.setDevice(dvo);
			list.add(callRecordVO);
		}
		return list;
	}
	
	/**
	 * 
	 * 方法的描述: paging中的result中的对象有对象属性例如本方法中的CallRecord中有Device类型的属性，需要进行转换。
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-22 下午1:39:08
	 * @param paging
	 * @param ignoreProperties	忽略的属性集
	 * @param xmlDateProperties	日期属性集
	 * @return
	 */
	public PagingVO<CallRecordVO> convertToPagingVO(Paging paging,String[] ignoreProperties,
			String... xmlDateProperties){
		PagingVO<CallRecordVO> pagingVO = new PagingVO<CallRecordVO>();
		if(paging == null)
			return pagingVO;
					
		org.springframework.beans.BeanUtils.copyProperties(paging, pagingVO);
		
		List<CallRecordVO> voResults = new ArrayList<CallRecordVO>();
		List<Object> results = paging.getResults();
		if(!CollectionUtils.isEmpty(results)){
			for (int index = 0, size = results.size(); index < size; index++) {
				CallRecordVO vo = new CallRecordVO();
				DeviceTypeVO deviceTypeVO = new DeviceTypeVO();
				DeviceVO deviceVO = new DeviceVO();
				CallRecord cr = (CallRecord) results.get(index);
				if(cr.getDevice() != null)
					copyProperties(cr.getDevice().getDeviceType(),deviceTypeVO);
				copyProperties(cr, vo, ignoreProperties,true, xmlDateProperties);
				deviceVO = deviceService.deviceToVO(cr.getDevice());
				if(deviceVO != null)
					deviceVO.setDeviceType(deviceTypeVO);
				vo.setDevice(deviceVO);
				voResults.add(vo);
			}
		}
		pagingVO.setResults(voResults);
		
		return pagingVO;
	}

	@Override
	public Long recordCount(CallRecordVO callRecord) {
		CallRecord _callRecord = new CallRecord();
		if(callRecord != null){
			Device d = new Device();
			DeviceType deviceType = new DeviceType();
			copyProperties(callRecord, _callRecord,new String[]{DEVICE},false, CALL_TIME,BEGIN_TIME,END_TIME);
			d = deviceService.voToDevice(callRecord.getDevice());
			d.setDeviceType(deviceType);
			_callRecord.setDevice(d);
		}
		return getSmartHomeService().callRecordCount(_callRecord);
	}

	@Override
	public Json saveCallRecordByJson(String json) {
		Json js = new Json();
		JSONObject jsonObj = JsonUtil.jsonStringToJsonObject(json);
		String callId = jsonObj.getString("id");
		String deviceCode = jsonObj.getString("deviceCode");
		String caller = jsonObj.getString("caller").equals("null")?null:jsonObj.getString("caller");
		String callType = jsonObj.getString("callType");
		String pictureLocalPath = jsonObj.getString("pictureLocalPath").equals("null")?null:jsonObj.getString("pictureLocalPath");
		String pictureNetPath = jsonObj.getString("pictureNetPath").equals("null")?null:jsonObj.getString("pictureNetPath");
		String audioLocalPath = jsonObj.getString("audioLocalPath").equals("null")?null:jsonObj.getString("audioLocalPath");
		String audioNetPath = jsonObj.getString("audioNetPath").equals("null")?null:jsonObj.getString("audioNetPath");
		Long callTime = Long.parseLong(jsonObj.getString("callTime"));
		Long talkTime = jsonObj.getLong("talkTime");
		String videoLocalPath = jsonObj.getString("videoLocalPath").equals("null")?null:jsonObj.getString("videoLocalPath");
		String videoNetPath = jsonObj.getString("videoNetPath").equals("null")?null:jsonObj.getString("videoNetPath");
		Device device = getSmartHomeService().queryDeviceByCode(deviceCode);
		CallRecord callRecord = new CallRecord();
		callRecord.setCallId(callId);
		callRecord.setDeviceCode(deviceCode);
		callRecord.setCaller(caller);
		callRecord.setDevice(device);
		callRecord.setCallType(callType);
		callRecord.setPictureLocalPath(FileUploadUtil.replaceFilePath(pictureLocalPath));
		callRecord.setAudioLocalPath(FileUploadUtil.replaceFilePath(audioLocalPath));
		callRecord.setAudioNetPath(FileUploadUtil.replaceFilePath(audioNetPath));
		callRecord.setCallTime(DateTimeUtil.convertDateToXMLGregorianCalendar(new Date(callTime)));
		callRecord.setPictureNetPath(FileUploadUtil.replaceFilePath(pictureNetPath));
		callRecord.setTalkTime(new Long(talkTime));
		callRecord.setVideoLocalPath(FileUploadUtil.replaceFilePath(videoLocalPath));
		callRecord.setVideoNetPath(FileUploadUtil.replaceFilePath(videoNetPath));
		try{
			getSmartHomeService().saveCallRecord(callRecord);
			js.setCode(AppDataConstant.SUCCESS);
		}catch(Exception e){
			js.setCode(AppDataConstant.FAILTRUE);
		}
  		js.setDeviceNo(deviceCode);
    	return js;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Override
	public Json saveCallRecordMessageByJson(String json) {
		Json js = new Json();
		JSONObject jsonObj = JsonUtil.jsonStringToJsonObject(json);
		String callId = jsonObj.getString("id").equals("null")?null:jsonObj.getString("id");
		String deviceCode = jsonObj.getString("deviceNo");
		String caller = jsonObj.getString("caller").equals("null")?null:jsonObj.getString("caller");
		String pictureLocalPath = jsonObj.getString("photo").equals("null")?null:jsonObj.getString("photo");
		//String pictureNetPath = jsonObj.getString("photo").equals("null")?null:jsonObj.getString("photo");
		//String audioLocalPath = jsonObj.getString("audioLocalPath").equals("null")?null:jsonObj.getString("audioLocalPath");
		//String audioNetPath = jsonObj.getString("audioNetPath").equals("null")?null:jsonObj.getString("audioNetPath");
		Long callTime = Long.parseLong(jsonObj.getString("callTime"));
		Long talkTime = jsonObj.getLong("recordTime");
		String videoLocalPath = jsonObj.getString("recordFilePath").equals("null")?null:jsonObj.getString("recordFilePath");
		String videoNetPath = jsonObj.getString("recordNetPath").equals("null")?null:jsonObj.getString("recordNetPath");
		Device device = getSmartHomeService().queryDeviceByCode(deviceCode);
		CallRecord callRecord = new CallRecord();
		callRecord.setCallId(callId);
		callRecord.setDeviceCode(deviceCode);
		callRecord.setCaller(caller);
		callRecord.setDevice(device);
		callRecord.setCallType(Constants.CALL_TYPE_MESSAGE);
		callRecord.setPictureLocalPath(FileUploadUtil.replaceFilePath(pictureLocalPath));
		callRecord.setCallTime(DateTimeUtil.convertDateToXMLGregorianCalendar(new Date(callTime)));
		//callRecord.setPictureNetPath(pictureNetPath);
		callRecord.setTalkTime(new Long(talkTime));
		callRecord.setVideoLocalPath(FileUploadUtil.replaceFilePath(videoLocalPath));
		callRecord.setVideoNetPath(FileUploadUtil.replaceFilePath(videoNetPath));
		try{
			getSmartHomeService().saveCallRecord(callRecord);
			js.setCode(AppDataConstant.SUCCESS);
		}catch(Exception e){
			js.setCode(AppDataConstant.FAILTRUE);
		}
  		js.setDeviceNo(deviceCode);
    	return js;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
}


