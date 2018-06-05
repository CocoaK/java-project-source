package com.biencloud.smarthome.web.gate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.FileUploadUtil;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.gate.service.IGateCardVisitService;
import com.biencloud.smarthome.web.gate.vo.GateCardVisitVO;
import com.biencloud.smarthome.web.sysparam.service.ISysParamService;
import com.biencloud.smarthome.web.wsclient.stub.Device;
import com.biencloud.smarthome.web.wsclient.stub.GateCard;
import com.biencloud.smarthome.web.wsclient.stub.GateCardVisit;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 门卡刷卡管理调用服务实现。
 * @author kouy
 * @since 1.0 2012-5-10
 */
public class GateCardVisitServiceImpl extends BaseService<GateCardVisitVO> implements
		IGateCardVisitService {

	private static final String VISIT_TIME = "visitTime";
	private static final String BEGIN_TIME = "beginTime";
	private static final String END_TIME = "endTime";
	private ISysParamService sysParamService;
	
	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	@Override
	public PagingVO<GateCardVisitVO> queryGateCardVisitsForPaging(
			GateCardVisitVO gateCardVisit, int pageNum, int pageSize) {
		GateCardVisit gcv = new GateCardVisit();
		copyProperties(gateCardVisit, gcv, false, 
				VISIT_TIME, BEGIN_TIME, END_TIME);
		
		Paging paging = getSmartHomeService().queryGateCardVisitsForPaging(
				gcv, pageNum, pageSize);		
		
		return convertToPagingVO(paging, VISIT_TIME, BEGIN_TIME, END_TIME);
	}

	@Override
	public GateCardVisitVO getGateCardVisitDetail(String visitId) {
		GateCardVisitVO gcvVO = new GateCardVisitVO();
		copyProperties(getSmartHomeService().getGateCardVisitDetail(visitId), 
				gcvVO, true, VISIT_TIME, BEGIN_TIME, END_TIME);
		return gcvVO;
	}

	@Override
	public List<GateCardVisitVO> queryGateCardVisits(GateCardVisitVO gateCardVisit) {
		GateCardVisit gcVisit = new GateCardVisit();
		String webDownloadUrl = "";
		try {
			webDownloadUrl = sysParamService.getWebDownloadAbsoluteUrl();
		} catch (Exception e) {
			logger.error("************获取Web下载绝对地址发生异常，错误信息如下：{}************", e);
		}
		copyProperties(gateCardVisit,gcVisit,false,VISIT_TIME, BEGIN_TIME, END_TIME);
		List<GateCardVisit> gcv = getSmartHomeService().queryAllGateCardVisits(gcVisit);
		List<GateCardVisitVO> list = new ArrayList<GateCardVisitVO>();
		if(gcv != null && gcv.size() > 0){
			for(GateCardVisit obj : gcv){
				GateCardVisitVO vo = new GateCardVisitVO();
				if(StringUtils.isNotBlank(obj.getPicPath1()))
					obj.setPicPath1(webDownloadUrl+obj.getPicPath1());
				if(StringUtils.isNotBlank(obj.getPicPath2()))
					obj.setPicPath2(webDownloadUrl+obj.getPicPath2());
				if(StringUtils.isNotBlank(obj.getPicPath3()))
					obj.setPicPath3(webDownloadUrl+obj.getPicPath3());
				copyProperties(obj,vo,true,VISIT_TIME, BEGIN_TIME, END_TIME);
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public void saveGateCardVisit(GateCardVisitVO gateCardVisitVO) {
		GateCardVisit gateCardVisit = new GateCardVisit();
		copyProperties(gateCardVisitVO,gateCardVisit,false,VISIT_TIME, BEGIN_TIME, END_TIME);
		getSmartHomeService().saveGateCardVisit(gateCardVisit);
		
	}

	@Override
	public void removeGateCardVisit(GateCardVisitVO gateCardVisitVO) {
		GateCardVisit gateCardVisit = new GateCardVisit();
		copyProperties(gateCardVisitVO,gateCardVisit,false,VISIT_TIME, BEGIN_TIME, END_TIME);
		getSmartHomeService().removeGateCardVisit(gateCardVisit);
		
	}

	@Override
	public void updateGateCardVisit(GateCardVisitVO gateCardVisitVO) {
		GateCardVisit gateCardVisit = new GateCardVisit();
		copyProperties(gateCardVisitVO,gateCardVisit,false,VISIT_TIME, BEGIN_TIME, END_TIME);
		getSmartHomeService().updateGateCardVisit(gateCardVisit);
	}

	@Override
	public Json saveGateCardVisit(String jsonString) {
		GateCard gateCard = null;
		GateCardVisitVO gateCardVisitVO = new GateCardVisitVO();
		GateCardVisit gateCardVisit = new GateCardVisit();
		Json json = new Json();
		json.setCode(AppDataConstant.FAILTRUE);
		JSONObject jsonObj = JsonUtil.jsonStringToJsonObject(jsonString);
		String cardNo = jsonObj.getString("cardNo");
		String deviceNo = jsonObj.getString("deviceNo");
		String picPath1 = jsonObj.getString("picPath1");
		String picPath2 = jsonObj.getString("picPath2");
		String picPath3 = jsonObj.getString("picPath3");
		Long visitTime = jsonObj.getLong("visitTime");
		String deviceAlias = null;
		String districtId = null;
		String ownerIdCard = null;
		String ownerName = null;
		String gateCardId = null;
		Device device = getSmartHomeService().queryDeviceByCode(deviceNo);	//根据刷卡机器的设备编号查询设备
		if(device != null && device.getHousingDistrictInfo() != null){
			deviceAlias = device.getDeviceAlias();
			//根据小区和卡号查询门卡
			gateCard = getSmartHomeService().queryGateCardByCardNo(device.getHousingDistrictInfo().getId(),cardNo);
		}
		if(gateCard != null){	//如果门卡存在，才赋值和保存
			districtId = gateCard.getDistrictId();
			ownerIdCard = gateCard.getOwnerIdCard();
			ownerName = gateCard.getOwnerName();
			gateCardId = gateCard.getGateCardId();
			
			gateCardVisitVO.setCardNo(cardNo);
			gateCardVisitVO.setDeviceCode(deviceNo);
			gateCardVisitVO.setDeviceAlias(deviceAlias);
			gateCardVisitVO.setDistrictId(districtId);
			gateCardVisitVO.setOwnerIdCard(ownerIdCard);
			gateCardVisitVO.setOwnerName(ownerName);
			gateCardVisitVO.setPicPath1(FileUploadUtil.replaceFilePath(picPath1));
			gateCardVisitVO.setPicPath2(FileUploadUtil.replaceFilePath(picPath2));
			gateCardVisitVO.setPicPath3(FileUploadUtil.replaceFilePath(picPath3));
			gateCardVisitVO.setVisitTime(new Date(visitTime));
			gateCardVisitVO.setGateCardId(gateCardId);
			copyProperties(gateCardVisitVO,gateCardVisit,false,VISIT_TIME, BEGIN_TIME, END_TIME);
			try{
				getSmartHomeService().saveGateCardVisit(gateCardVisit);
				json.setCode(AppDataConstant.SUCCESS);
			}catch(Exception e){
				logger.error("************保存门卡刷卡记录发生异常，错误信息如下：{}************", e);
			}
		}
		json.setDeviceNo(deviceNo);
		return json;
	}
}
